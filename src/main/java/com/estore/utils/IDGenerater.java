package com.estore.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

public class IDGenerater {

    /**
     * 起始的时间戳
     */
    private static final long START_TIMESTAMP = 1361753741828L;

    /**
     * 毫秒内自增位：10位支持1024个
     */
    private static final long SEQUENCE_BITS = 10L;

    /**
     * IP标识位数
     */
    private static final long IP_BITS = 8L;

    /**
     * PID标识位数
     */
    private static final long PID_BITS = 5L;

    /**
     * 机器标识位数：13位支持8192台机器
     */
    private static final long WORKER_ID_BITS = IP_BITS + PID_BITS;

    /**
     * 时间毫秒左移位数
     */
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 序列号左移位数
     */
    private static final long SEQUENCE_SHIFT = WORKER_ID_BITS;

    /**
     * 序列号最大值
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    /**
     * 机器ID最大值
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 上次生成ID时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 机器ID
     */
    private final long workerId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    private static final IDGenerater WORKER = new IDGenerater();

    private IDGenerater() {
        long id = getWorkerId();
        if (id > MAX_WORKER_ID || id < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",
                    MAX_WORKER_ID));
        }
        this.workerId = id;
    }

    private long nextId() {
        synchronized (this) {
            long timestamp = currentTimestamp();
            if (this.lastTimestamp == timestamp) {
                this.sequence = (this.sequence + 1) & MAX_SEQUENCE;
                if (this.sequence == 0) {
                    timestamp = tilNextMillis(this.lastTimestamp);
                }
            } else {
                this.sequence = 0;
            }

            if (timestamp < this.lastTimestamp) {
//                logger.msg("Clock moved backwards. Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp).error();
            }

            this.lastTimestamp = timestamp;
            return ((timestamp - START_TIMESTAMP) << TIMESTAMP_SHIFT) | (this.sequence << SEQUENCE_SHIFT) | this.workerId;
        }
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = currentTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTimestamp();
        }
        return timestamp;
    }

    private long currentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取唯一ID，需要传入用户userId
     * 最长为23位，因为long类型最大为19位长度
     * <p>
     * 若不传，在生成19位唯一ID
     *
     * @param ids
     * @return BigInteger
     */
    public static String getUniId(Long... ids) {
        String last4BitUserId = "";
        if (null == ids || ids.length == 0) {
            last4BitUserId = "";
        } else {
            Long userId = ids[0];

            if (1000 >= userId) {
                last4BitUserId = userId + "";
            } else {
                last4BitUserId = StringUtils.right(userId + "", 4);
            }
        }

//        return new BigInteger(WORKER.nextId() + "" + last4BitUserId);
        return WORKER.nextId() + "" + last4BitUserId;
    }


    /**
     * 生成机器ID
     */
    private static long getWorkerId() {
        byte[] ip;

        long workerId;

        try {
            ip = getPrivateIp().getAddress();
            workerId = (ip[2] << 8 | (int) ip[3]) & ((1 << IP_BITS) - 1);
        } catch (SocketException e) {
            throw new RuntimeException("无法获取本机IP地址", e);
        }

        long pid = getPid() & ((1 << PID_BITS) - 1);

        return (workerId << PID_BITS) | pid;
    }

    /**
     * 取得本机内网IP
     */
    private static InetAddress getPrivateIp() throws SocketException {
        List<InetAddress> addresses = getAllIPs();
        if (CollectionUtils.isEmpty(addresses)) {
//            throw new RuntimeException( "无法获取本机IP地址");
        }
        List<InetAddress> localAddresses = new ArrayList();
        for (InetAddress address : addresses) {
            if (address.isSiteLocalAddress()) {
                localAddresses.add(address);
            }
        }

        if (1 != localAddresses.size()) {
            StringBuilder ips = new StringBuilder();
            for (InetAddress address : localAddresses) {
                ips.append(address.getHostAddress()).append(",");
            }
            ips.deleteCharAt(ips.length() - 1);
            throw new RuntimeException("内网IP个数不等于1, size=" + localAddresses.size() + ", ip={" + ips + "}");
        }

        return addresses.get(0);
    }

    /**
     * 取得本机所有IP
     */
    private static List<InetAddress> getAllIPs() throws SocketException {
        List<InetAddress> result = new ArrayList();
        Enumeration<NetworkInterface> netInterfaces;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip;
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = addresses.nextElement();
                if (!ip.getHostAddress().contains(":")) {
                    result.add(ip);
                }
            }
        }
        return result;
    }

    /**
     * 取得PID
     */
    private static int getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
//        logger.msg("当前进程的标识为："+name).info();
        int index = name.indexOf('@');
        if (index == -1) {
            throw new RuntimeException("获取PID错误, name=" + name);
        }

        try {
            int pid = Integer.parseInt(name.substring(0, index));
//            logger.msg("当前进程的PID为："+pid).info();
            return pid;
        } catch (NumberFormatException e) {
            throw new RuntimeException("获取PID错误, name=" + name);
        }
    }

    public static void main(String[] args) {

        List<Integer> intList = Lists.newArrayList();
        for (int i = 0; i < 10000000; i++) {
//            System.out.println(Long.toBinaryString(id));
//            System.out.println(WORKER.getUniId(12342345l));
            intList.add(i);
        }

        Set<String> uidSet = Sets.newConcurrentHashSet();
        Set<String> repeatSet = Sets.newConcurrentHashSet();

        intList.parallelStream().forEach(i -> {
            String id = IDGenerater.getUniId(Long.valueOf(i));
            if (uidSet.contains(id)) {
                System.out.println("repeat:" + id);
                repeatSet.add(id);
            } else {
                uidSet.add(id);
            }
        });

        System.out.println(uidSet.size());
        System.out.println(repeatSet.size());

    }
}
