import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ReflactTest {
    public static void main(String[] args) {
        try {
            String name = null;
            Class<?> clazz = Class.forName("com.estore.service.impl.UserServiceImpl");
            //获取本类的所有方法，存放入数组
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if(StringUtils.equals("queryUserByName",method.getName())){
                    System.out.println("方法名："+method.getName());
                    //获取本方法所有参数类型，存入数组
                    Parameter[] getTypeParameters = method.getParameters();
                    name = getTypeParameters[0].getName();
                }
            }
            System.out.println(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
