import com.estore.bean.Product;
import com.estore.dao.ProductMapper;
import com.estore.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void queryProductByFlagTest(){
        Set<Product> productsList = productService.queryProductByFlag("3");
        System.out.println(productsList.size());
        productsList.parallelStream().forEach(product -> System.out.println(product.getImages()));
    }

    @Test
    public void queryProductByCatIdTest(){
        //List<Product> productsList = productService.queryProductByCategoryId(1);
       // Set<Product> productsList = productService.queryProductByCateDetailId(1);
        //Set<Product> productsList = productService.queryProductByName("爱%");
        Set<Product> productsList = productService.queryProductByPriceRank(new BigDecimal(100),new BigDecimal(159));
        System.out.println(productsList.size());
        productsList.parallelStream().forEach(product -> System.out.println(product.getName()+" ==="+product.getCategoryDetailId()));
    }

}
