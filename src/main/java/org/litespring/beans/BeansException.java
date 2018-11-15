package org.litespring.beans;

/**
 * @author yunfy
 * @create 2018-11-15 23:38
 **/
public class BeansException extends RuntimeException {
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
