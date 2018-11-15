package org.litespring.context.support;

import org.litespring.beans.factory.support.DefalutBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;

/**
 * @author yunfy
 * @create 2018-11-16 23:40
 **/
public class ClassPathXmlApplicationContext implements ApplicationContext {
    private DefalutBeanFactory factory;

    public ClassPathXmlApplicationContext(String configFile) {
        factory = new DefalutBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(configFile);
    }

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }


}
