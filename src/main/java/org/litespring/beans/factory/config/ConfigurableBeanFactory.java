package org.litespring.beans.factory.config;

import org.litespring.beans.factory.BeanFactory;

/**
 * 用于配置
 *
 * @author yunfy
 * @create 2018-12-05 23:02
 **/
public interface ConfigurableBeanFactory extends BeanFactory {
    /**
     * set class loader
     *
     * @param beanClassLoader
     */
    void setBeanClassLoader(ClassLoader beanClassLoader);

    /**
     * get class loader
     *
     * @return
     */
    ClassLoader getBeanClassLoader();
}
