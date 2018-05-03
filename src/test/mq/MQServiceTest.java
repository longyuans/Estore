import com.estore.service.MQService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class MQServiceTest {

    @Autowired
    private MQService mqService;

    @Test
    public void test() throws IOException, TimeoutException {
            mqService.sendMessage("Estore.hello,springMQ");
    }
}
