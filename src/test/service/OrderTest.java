import com.estore.model.OrderModel;
import com.estore.service.OrderLineService;
import com.estore.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class OrderTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderLineService orderLineService;

    @Test
    public void TestCreateOrder() throws Exception {
        OrderModel orderModel = new OrderModel();
        orderModel.setPayWay("支付宝");
        orderModel.setUserId(3);
        orderModel.setReceiverId(2);
        orderModel.setOrderLineModels(orderLineService.queryAllOrderLine(3));
        orderModel.setMoney(new BigDecimal(400));
        orderService.createOrder(orderModel);
    }
}
