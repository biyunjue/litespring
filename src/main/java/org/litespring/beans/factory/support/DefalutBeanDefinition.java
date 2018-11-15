package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * @author yunfy
 * @create 2018-11-15 23:23
 **/
public class DefalutBeanDefinition implements BeanDefinition {
    public String id;
    public String beanClassName;

    public DefalutBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }


    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }
}
