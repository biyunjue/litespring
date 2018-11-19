package org.litespring.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yunfy
 * @create 2018-11-16 23:55
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationContextTest.class,
        BeanFactoryTest.class, ResourceTest.class})
public class V1AllTests {


}
