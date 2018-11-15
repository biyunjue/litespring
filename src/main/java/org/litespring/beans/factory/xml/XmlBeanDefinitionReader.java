package org.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.DefalutBeanDefinition;
import org.litespring.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author yunfy
 * @create 2018-11-16 23:01
 **/
public class XmlBeanDefinitionReader {

    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    public void loadBeanDefinitions(String configFile) {
        InputStream in = null;
        try {
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            in = cl.getResourceAsStream(configFile);
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            //beans
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element ele = (Element) iterator.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new DefalutBeanDefinition(id, beanClassName);
                this.registry.registerBeanDefinition(id, bd);
            }

        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("IoException parsing XML document", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
