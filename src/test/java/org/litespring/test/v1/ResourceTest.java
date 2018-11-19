package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yunfy
 * @create 2018-11-19 23:04
 **/
public class ResourceTest {

    @Test
    public void testClassPathResource() {
        Resource r = new ClassPathResource("petstore-v1.xml");
        InputStream in = null;
        try {
            in = r.getInputStream();
            Assert.assertNotNull(in);
        } catch (Exception ex) {

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {

            }
        }
    }


    @Test
    public void testFileSystemResource() {
        Resource r = new FileSystemResource("D:\\study\\projects\\litespring\\src\\test\\resources\\petstore-v1.xml");
        InputStream in = null;
        try {
            in = r.getInputStream();
            Assert.assertNotNull(in);
        } catch (Exception ex) {

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {

            }
        }
    }


}
