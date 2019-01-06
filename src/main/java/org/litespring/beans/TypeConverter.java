package org.litespring.beans;

/**
 * @author yunfy
 * @create 2019-01-06 22:55
 **/
public interface TypeConverter {


    /**
     * 类型转换
     *
     * @param value
     * @param requiredType
     * @param <T>
     * @return
     * @throws TypeMismatchException
     */
    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;

}
