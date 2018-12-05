package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurablebeanFactory;
import org.litespring.utils.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yunfy
 * @create 2018-11-15 22:52
 **/
public class DefalutBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurablebeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);

    private ClassLoader beanClassLoader;

    public DefalutBeanFactory() {
    }

    /**
     * 注册一个BeanDefinition
     *
     * @param beanID
     * @param bd
     */
    @Override
    public void registerBeanDefinition(String beanID, BeanDefinition bd) {
        this.beanDefinitionMap.put(beanID, bd);
    }

    /**
     * 从容器获取BeanDefinition
     *
     * @param beanID
     * @return
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanID) {
        return this.beanDefinitionMap.get(beanID);
    }

    @Override
    public Object getBean(String beanID) {
        BeanDefinition bd = this.getBeanDefinition(beanID);
        if (bd == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
        if (bd.isSingleton()) {
            Object bean = this.getSingleton(beanID);
            if (bean == null) {
                bean = createBean(bd);
                this.registerSingleton(beanID, bean);
            }
            return bean;
        }
        return createBean(bd);

    }

    /**
     * 根据BeanDefinition创建bean
     *
     * @param bd bean的定义
     * @return
     */
    private Object createBean(BeanDefinition bd) {
        ClassLoader cl = this.getBeanClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Create bean " + beanClassName + " fail");
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader == null) ? ClassUtils.getDefaultClassLoader() : this.beanClassLoader;
    }
}
