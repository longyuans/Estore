package com.estore.redis;

import com.estore.utils.EstoreException;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class RedisUtils {
    public static void set(String key,String value) throws EstoreException {
        if (StringUtils.isEmpty(key)){
            throw new EstoreException("","set() redis参数为空");
        }
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("127.0.0.1",6379));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode);
        jedisCluster.set(key,value);
    }
    public static String get(String key) throws EstoreException {
        if (StringUtils.isEmpty(key)){
            throw new EstoreException("","get() redis参数为空");
        }
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("127.0.0.1",6379));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode);
        return jedisCluster.get(key);
    }
}
