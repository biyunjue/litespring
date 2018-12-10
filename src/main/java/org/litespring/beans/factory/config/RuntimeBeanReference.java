package org.litespring.beans.factory.config;

/**
 * 用于在BeanDefinition中定义一个被依赖的Bean名称
 *
 * @author yunfy
 * @create 2018-12-11 23:22
 **/
public class RuntimeBeanReference {
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }
}
