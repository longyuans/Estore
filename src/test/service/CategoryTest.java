import com.estore.model.CategoryModel;
import com.estore.service.CategoryService;
import com.estore.utils.EstoreException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class CategoryTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void queryCategoryTest() throws EstoreException {
        List<CategoryModel> modelList = categoryService.queryAllCategory();
        System.out.println(modelList.size());
        modelList.parallelStream().forEachOrdered(categoryModel -> System.out.println(categoryModel));
    }
}
