package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * @author yunfy
 * @create 2018-11-16 0:07
 **/
public interface BeanDefinitionRegistry {
    /**
     * 从容器中获取BeanDefinition
     *
     * @param beanID
     * @return
     */
    BeanDefinition getBeanDefinition(String beanID);

    /**
     * 注册一个BeanDefinition，放入容器中
     *
     * @param beanID
     * @param bd
     */
    void registerBeanDefinition(String beanID, BeanDefinition bd);


}
