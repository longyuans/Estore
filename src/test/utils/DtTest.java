

import com.estore.utils.DbLocakUtils;
import redis.clients.jedis.JedisPool;
        import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by liuyang on 2017/4/20.
 */

public class DtTest {
    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 500; i++) {
            ThreadB threadB = new ThreadB(service);
            threadB.start();
        }
    }
}

 class Service {
    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "127.0.0.1", 6379, 3000);
    }

     DbLocakUtils lock = new DbLocakUtils(pool);

    int n = 500;

    public void seckill() {
        // 返回锁的value值，供释放锁时候进行判断
        String indentifier = lock.lockWithTimeout("resource", 5000, 1000);
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        System.out.println(--n);
        lock.releaseLock("resource", indentifier);
        System.out.println(Thread.currentThread().getName() + "释放了锁");
    }
}

class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}
