package org.litespring.beans.factory.support;

import org.apache.commons.beanutils.BeanUtils;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.utils.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yunfy
 * @create 2018-11-15 22:52
 **/
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);

    private ClassLoader beanClassLoader;

    public DefaultBeanFactory() {
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

    private Object createBean(BeanDefinition bd) {
        //创建实例
        Object bean = instantiateBean(bd);
        //组装属性
        populateBeanWithBeanutils(bd, bean);
        return bean;
    }


    /**
     * 根据BeanDefinition创建bean
     *
     * @param bd bean的定义
     * @return
     */
    private Object instantiateBean(BeanDefinition bd) {
        ClassLoader cl = this.getBeanClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Create bean " + beanClassName + " fail");
        }
    }

    /**
     * 组装属性
     *
     * @param bd
     * @param bean
     */
    private void populateBean(BeanDefinition bd, Object bean) {
        //得到需要set的属性
        List<PropertyValue> propertyValues = bd.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }
        //使用java内省
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        SimpleTypeConverter converter = new SimpleTypeConverter();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();


            for (PropertyValue pv : propertyValues) {
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                //转化成需要的真实实例
                Object resolvedValue = resolver.resolveValueIfNecessary(originalValue);
                //需要调用bean的set方法
                for (PropertyDescriptor pd : pds) {
                    if (pd.getName().equals(propertyName)) {
                        Object convertedValue = converter.convertIfNecessary(resolvedValue, pd.getPropertyType());
                        pd.getWriteMethod().invoke(bean, convertedValue);
                        break;
                    }
                }
            }
        } catch (Exception ex) {

            throw new BeanCreationException("Failed to obtain BeanInfo for class [" + bd.getBeanClassName() + "]", ex);
        }
    }


    /**
     * 组装属性
     *
     * @param bd
     * @param bean
     */
    private void populateBeanWithBeanutils(BeanDefinition bd, Object bean) {
        //得到需要set的属性
        List<PropertyValue> propertyValues = bd.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }
        //使用java内省
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        try {

            for (PropertyValue pv : propertyValues) {
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                //转化成需要的真实实例
                Object resolvedValue = resolver.resolveValueIfNecessary(originalValue);
                //需要调用bean的set方法
                BeanUtils.setProperty(bean, propertyName, resolvedValue);
            }
        } catch (Exception ex) {

            throw new BeanCreationException("Failed to obtain BeanInfo for class [" + bd.getBeanClassName() + "]", ex);
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
