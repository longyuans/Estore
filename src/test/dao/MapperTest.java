import com.estore.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author WZW
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class MapperTest {
	@Autowired
	SqlSession sqlsession;
	@Test
	public void testDepartment(){
/*		ApplicationContext ioc=new ClassPathXmlApplicationContext("src/main/resources/spring/applicationContext.xml");
		DepartmentMapper bean=ioc.getBean(DepartmentMapper.class);*/
		//System.out.println(departmentMapper);
		
		//departMent����
/*		departmentMapper.insertSelective(new Department((long) 1, "���Բ���"));
		departmentMapper.insertSelective(new Department((long) 2, "��������"));*/
		
		//Employee����
		//employeeMapper.insertSelective(new Employee((long) 3, "����", "��",null, null,null));
		//employeeMapper.insertSelective(new Employee("tian", "woman",null, (long) 1,null));
		
/*		
		//����������Ա��
		EmployeeMapper mapper=sqlsession.getMapper(EmployeeMapper.class);
		for(long i=300;i<400;i++){
			String uid=UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new Employee(i, uid, "M", uid+"@163.com",(long) 1, null));
		}
		System.out.println("����ִ�н���================");*/
	}
	@Test
	public void testEmployee(){
		UserMapper mapper=sqlsession.getMapper(UserMapper.class);
		System.out.println(mapper);
	}
}
