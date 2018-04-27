import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class RedisClusterTest {
    public static void main(String[] args) {
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("127.0.0.1",6379));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode);
        //jedisCluster.set("key1","first");
        String s = jedisCluster.get("CategoryDetail");
        System.out.println(s);
    }
}
