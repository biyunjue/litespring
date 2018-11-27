package org.litespring.context.support;

import org.litespring.beans.factory.support.DefalutBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;

/**
 * @author yunfy
 * @create 2018-11-27 23:36
 **/
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefalutBeanFactory factory;

    public AbstractApplicationContext(String configFile) {
        factory = new DefalutBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(configFile);
        reader.loadBeanDefinitions(resource);
    }

    /**
     * 通过路径获取资源
     *
     * @param path
     * @return
     */
    abstract Resource getResourceByPath(String path);

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }

}
