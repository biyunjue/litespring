package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

/**
 * @author yunfy
 * @create 2018-11-15 23:41
 **/
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
