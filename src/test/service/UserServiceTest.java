import com.estore.bean.User;
import com.estore.estoreEnum.OperateEnum;
import com.estore.model.BaseResponse;
import com.estore.service.UserService;
import com.estore.utils.IDGenerater;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testCreate() throws Exception {
        User user = new User();
        user.setOperate(OperateEnum.createUser.toString());
        user.setSourceId((int)(Math.random()*10000)+"");
        user.setName("赵六2");
        user.setPassword("1234");
        BaseResponse response = userService.createUser(user);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setOperate(OperateEnum.updateUser.toString());
        user.setSourceId((int)(Math.random()*10000)+"");
        user.setId(3);
        user.setPassword("12345");
        BaseResponse response = userService.updateUser(user);
        System.out.println(response);
        Assert.assertNotNull(response);
    }
}
