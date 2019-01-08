package org.litespring.test.v3;

import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v3.PetStoreService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author yunfy
 * @create 2019-01-08 22:12
 **/
public class ApplicationContextTestV3 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStore");

        assertNotNull(petStoreService.getAccountDao());
        assertNotNull(petStoreService.getItemDao());
        assertEquals(1, petStoreService.getVersion());

    }
}
