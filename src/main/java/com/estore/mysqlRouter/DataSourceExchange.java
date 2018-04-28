package com.estore.mysqlRouter;

import com.estore.estoreEnum.DataSourceEnum;
import com.estore.mq.Comsumer;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 切面类，目前还只切serviceImpl;在userService的createUser中有手动setDataSource测试。
 */
public class DataSourceExchange {

    private String UserName;
    private Logger logger = LoggerFactory.getLogger(Comsumer.class);

    public void before(JoinPoint point) {

     /*   //获取目标对象的类类型，
        String name = null;
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.estore.service.impl.UserServiceImpl");
            //获取本类的所有方法，存放入数组
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                if (StringUtils.equals("queryUserByName", method.getName())) {
                    //获取本方法所有参数类型，存入数组
                    Parameter[] getTypeParameters = method.getParameters();
                    name = getTypeParameters[0].getName();
                }
            }
            if (StringUtils.isNotEmpty(UserName)) {
                if (UserName.hashCode() % 2 == 0) {
                    logger.debug(UserName+"=="+UserName.hashCode() % 2);
                    DataSourceHolder.setDataSources(DataSourceEnum.ds1.getKey());
                    logger.debug("当前用户选中的dataSource是 resp = {} ds1");
                } else {
                    logger.debug(UserName+"=="+UserName.hashCode() % 2);
                    DataSourceHolder.setDataSources(DataSourceEnum.ds2.getKey());
                    logger.debug("当前用户选中的dataSource是 resp = {} ds2");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    /*    *//**
     * 执行后将数据源置为空
     *//*
    public void after() {
        DataSourceHolder.setDataSources(null);
    }*/

}
