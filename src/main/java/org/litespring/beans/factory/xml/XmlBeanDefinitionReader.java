package org.litespring.beans.factory.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.DefaultBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author yunfy
 * @create 2018-11-16 23:01
 **/
public class XmlBeanDefinitionReader {
    private static final Log logger = LogFactory.getLog(XmlBeanDefinitionReader.class);

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    public static final String TYPE_ATTRIBUTE = "type";

    private BeanDefinitionRegistry registry;


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    public void loadBeanDefinitions(Resource resource) {
        InputStream in = null;
        try {
            in = resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            //beans
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element ele = (Element) iterator.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new DefaultBeanDefinition(id, beanClassName);
                if (ele.attributeValue(SCOPE_ATTRIBUTE) != null) {
                    bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
                }
                //解析bean构造的属性
                parseConstructorArgElements(ele, bd);
                //解析bean依赖的属性
                parsePropertyElement(ele, bd);
                this.registry.registerBeanDefinition(id, bd);
            }

        } catch (Exception e) {
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

    /**
     * 解析constructor-arg属性
     *
     * @param beanEle
     * @param bd
     */
    public void parseConstructorArgElements(Element beanEle, BeanDefinition bd) {
        Iterator iter = beanEle.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while (iter.hasNext()) {
            Element ele = (Element) iter.next();
            parseConstructorArgElement(ele, bd);
        }

    }

    private void parseConstructorArgElement(Element ele, BeanDefinition bd) {
        String typeAttr = ele.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);
        Object value = parsePropertyValue(ele, bd, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }

        bd.getConstructorArgument().addArgumentValue(valueHolder);

    }

    /**
     * 解析Property属性
     *
     * @param beanElem
     * @param bd
     */
    private void parsePropertyElement(Element beanElem, BeanDefinition bd) {
        Iterator iterator = beanElem.elementIterator(PROPERTY_ELEMENT);
        while (iterator.hasNext()) {
            Element propElem = (Element) iterator.next();
            String propName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object val = parsePropertyValue(propElem, bd, propName);
            //创建一个Property对应的实例
            PropertyValue pv = new PropertyValue(propName, val);
            bd.getPropertyValues().add(pv);
        }

    }

    /**
     * 获取Property对应的ref或者value的值
     *
     * @param ele
     * @param bd
     * @param propertyName
     * @return
     */
    private Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";

        boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) != null);
        //判断是ref还是value
        if (hasRefAttribute) {
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            //返回这个依赖bean的定义
            return new RuntimeBeanReference(refName);
        } else if (hasValueAttribute) {
            return new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
        } else {
            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }
}
