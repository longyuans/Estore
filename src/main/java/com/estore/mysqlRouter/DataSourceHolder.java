package com.estore.mysqlRouter;

/**
 * DynamicDataSourceHolder用于存储当前线程中使用的数据源
 */
public class DataSourceHolder {

    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

    public static void setDataSources(String dataSource) {
        dataSources.set(dataSource);
    }

    public static String getDataSources() {
        return dataSources.get();
    }
}
