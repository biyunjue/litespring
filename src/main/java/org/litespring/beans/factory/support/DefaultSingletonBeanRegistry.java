package org.litespring.beans.factory.support;

import org.litespring.beans.factory.config.SingletonBeanRegistry;
import org.litespring.utils.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yunfy
 * @create 2018-12-06 0:18
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    public final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(64);

    @Override
    public void registerSingleton(String beanID, Object singletonObject) {
        Assert.notNull(beanID, "'beanID' must not be null");
        Object oldObject = this.singletonObjects.get(beanID);
        if (oldObject != null) {
            throw new IllegalStateException("Could not register object [" + singletonObject +
                    "] under bean name '" + beanID + "': there is already object [" + oldObject + "] bound");
        }
        this.singletonObjects.put(beanID, singletonObject);
    }

    @Override
    public Object getSingleton(String beanID) {
        return this.singletonObjects.get(beanID);
    }
}
