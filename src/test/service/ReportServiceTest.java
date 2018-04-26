import com.estore.bean.Report;
import com.estore.dao.ReportMapper;
import com.estore.service.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Test
    public void queryAllReportTest(){
        List<Report> reportList = reportService.queryAllReport();
        System.out.println(reportList.size());
        System.out.println(reportList.get(0));
    }
}
