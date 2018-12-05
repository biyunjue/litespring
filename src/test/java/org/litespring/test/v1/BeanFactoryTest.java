package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefalutBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.service.v1.AppleStoreService;
import org.litespring.service.v1.PetStoreService;

import static org.junit.Assert.*;

/**
 * @author yunfy
 * @create 2018-11-15 22:23
 **/
public class BeanFactoryTest {
    private DefalutBeanFactory factory = null;
    private XmlBeanDefinitionReader reader = null;

    @Before
    public void setUP() {
        //获取所有Bean的工厂类
        factory = new DefalutBeanFactory();
        //抽取一个专门的类用于解析xml，获取BeanDefinition
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void testGetSingletonBean() {
        //注册一个BeanDefinition
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
        //获取某个Bean的定义
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        //单例模式
        assertTrue(bd.isSingleton());
        assertFalse(bd.isPrototype());
        assertEquals(BeanDefinition.SCOPE_DEFAULT, bd.getScope());
        //断言获取的类名是xml中的类名
        assertEquals("org.litespring.service.v1.PetStoreService", bd.getBeanClassName());
        //获取类
        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");
        //断言类不为空
        assertNotNull(petStoreService);
        //再次获取
        PetStoreService petStoreService1 = (PetStoreService) factory.getBean("petStore");
        assertEquals(petStoreService, petStoreService1);
    }

    @Test
    public void testGetPrototypeBean() {
        //注册一个BeanDefinition
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
        //获取某个Bean的定义
        BeanDefinition bd = factory.getBeanDefinition("appleStore");
        //单例模式
        assertTrue(bd.isPrototype());
        assertFalse(bd.isSingleton());
        assertEquals(BeanDefinition.SCOPE_PROTOTYPE, bd.getScope());
        //断言获取的类名是xml中的类名
        assertEquals("org.litespring.service.v1.AppleStoreService", bd.getBeanClassName());
        //获取类
        AppleStoreService appleStoreService = (AppleStoreService) factory.getBean("appleStore");
        //断言类不为空
        assertNotNull(appleStoreService);
        //再次获取
        AppleStoreService appleStoreService1 = (AppleStoreService) factory.getBean("appleStore");
        assertNotEquals(appleStoreService, appleStoreService1);
    }

    @Test
    public void testInvalidBean() {
        //注册一个BeanDefinition
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
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
            //注册一个BeanDefinition
            reader.loadBeanDefinitions(new ClassPathResource("xxx-v1.xml"));
        } catch (BeanDefinitionStoreException ex) {
            return;
        }
        Assert.fail("expect exception");
    }
}

