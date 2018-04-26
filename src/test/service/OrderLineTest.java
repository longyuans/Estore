import com.estore.bean.OrderLine;
import com.estore.model.BaseResponse;
import com.estore.model.OrderLineModel;
import com.estore.service.OrderLineService;
import com.estore.service.ProductService;
import com.estore.service.UserService;
import com.estore.utils.EstoreException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class OrderLineTest {

    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Test
    public void addOrderLine(){
        try {
            OrderLineModel orderLineModel = new OrderLineModel();
            orderLineModel.setId(4);
            orderLineModel.setAmount(2);
            orderLineModel.setTotalPrice(new BigDecimal(220));
            orderLineModel.setUser(userService.queryUserByName("李四"));
            //BaseResponse response =orderLineService.addProductToShopCart(orderLineModel);
            BaseResponse response =orderLineService.updateOrderLineModel(orderLineModel);
            System.out.println(response);
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getSuccess(),true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void queryOrderLine() throws EstoreException {
        List<OrderLineModel> orderLineList = orderLineService.queryAllOrderLine(3);
        System.out.println(orderLineList.size());
        orderLineList.parallelStream().forEachOrdered(orderLineModel -> System.out.println(orderLineModel));
    }
}
