package org.litespring.beans.factory.config;

/**
 * @author yunfy
 * @create 2018-12-06 0:13
 **/
public interface SingletonBeanRegistry {

    /**
     * 注册一个单例对象
     *
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 获取一个单例对象
     *
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
