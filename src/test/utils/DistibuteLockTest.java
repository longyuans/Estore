import com.estore.utils.DistributeLockUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class DistibuteLockTest extends Thread {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void test() {
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(jedisCluster);
            threadA.start();
            System.out.println("第"+i+"次交易===============");
        }
    }

}

class ThreadA extends Thread {
    private JedisCluster jedisCluster;

    public ThreadA(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    private void seckill(JedisCluster jedisCluster) {
        DistributeLockUtils lock = new DistributeLockUtils(jedisCluster);
        // 返回锁的value值，供释放锁时候进行判断
        String indentifier = lock.lockWithTimeout("resource", 5000, 1000);
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        lock.releaseLock("resource", indentifier);
        System.out.println(Thread.currentThread().getName() + "获得了锁");
    }

    @Override
    public void run() {
        seckill(jedisCluster);
    }
}
