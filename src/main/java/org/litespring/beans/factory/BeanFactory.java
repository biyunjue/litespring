package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

/**
 * @author yunfy
 */
public interface BeanFactory {
    /**
     * 获取bean的定义
     *
     * @param beanID bean的ID
     * @return
     */
    BeanDefinition getBeanDefinition(String beanID);

    /**
     * 获取bean
     *
     * @param beanID bean的ID
     * @return
     */
    Object getBean(String beanID);
}
