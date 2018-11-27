package org.litespring.context.support;

import org.litespring.beans.factory.support.DefalutBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.FileSystemResource;

/**
 * @author yunfy
 * @create 2018-11-27 23:03
 **/
public class FileSystemXmlApplicationContext implements ApplicationContext {
    private DefalutBeanFactory factory;


    public FileSystemXmlApplicationContext(String configFile) {
        factory = new DefalutBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new FileSystemResource(configFile));
    }

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }
}
