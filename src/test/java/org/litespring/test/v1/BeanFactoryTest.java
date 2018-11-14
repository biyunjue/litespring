package org.litespring.test.v1;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.BeansException;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefalutBeanFactory;
import org.litespring.service.v1.PetStoreService;

/**
 * @author yunfy
 * @create 2018-11-15 0:23
 **/
public class BeanFactoryTest {

    @Test
    public void testGetBean() {
        //获取所有Bean的工厂类
        BeanFactory factory = new DefalutBeanFactory("petstore-v1.xml");
        //获取某个Bean的定义
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        //断言获取的类名是xml中的类名
        assertEquals("org.litespring.service.v1.PetStoreService", bd.getBeanClassName());
        //获取类
        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");
        //断言类不为空
        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean() {
        BeanFactory factory = new DefalutBeanFactory("petstore-v1.xml");
        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException ex) {
            return;
        }
        Assert.fail("expect exception");
    }

    @Test
    public void testInvalidXml() {
        try {
            new DefalutBeanFactory("xxx-v1.xml");
        } catch (BeanDefinitionStoreException ex) {
            return;
        }
        Assert.fail("expect exception");
    }
}

