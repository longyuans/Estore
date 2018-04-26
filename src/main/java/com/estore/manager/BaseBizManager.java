package com.estore.manager;

/**
 * 定义写服务的入口process模板方法
 *
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface BaseBizManager<T, R> {

    /**
     * process模板，用于处理通用写服务相关方法，包括处理幂等、记录日志、事务保证等
     *
     * @param request r
     * @return r
     * @throws Exception
     */
    R process(T request) throws Exception;
}
