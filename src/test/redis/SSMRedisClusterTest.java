import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class SSMRedisClusterTest {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void testSet(){
        jedisCluster.set("name", "firstSSMRedis");
        String val = jedisCluster.get("name");
        System.out.println(val);
    }
}
