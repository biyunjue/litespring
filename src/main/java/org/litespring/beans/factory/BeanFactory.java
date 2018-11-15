package org.litespring.beans.factory;

/**
 * @author yunfy
 */
public interface BeanFactory {
    /**
     * 获取bean
     *
     * @param beanID bean的ID
     * @return
     */
    Object getBean(String beanID);
}
