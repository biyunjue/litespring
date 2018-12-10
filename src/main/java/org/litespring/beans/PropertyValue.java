package org.litespring.beans;

/**
 * @author yunfy
 * @create 2018-12-11 23:13
 **/
public class PropertyValue {

    /**
     * 属性的名称
     */
    private final String name;
    /**
     * 属性的值
     */
    private final Object value;

    /**
     * 属性值如果是一个类的话，判断value
     * 是否已经转换成了convertedValue
     */
    private boolean converted = false;

    /**
     * 属性值如果是一个类的话，需要获得该类的实例存放于此
     */
    private Object convertedValue;


    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public synchronized boolean isConverted() {
        return this.converted;
    }

    /**
     * 转换value为实际的实例
     *
     * @param value
     */
    public synchronized void setConvertedValue(Object value) {
        this.converted = true;
        this.convertedValue = value;
    }

    public synchronized Object getConvertedValue() {
        return this.convertedValue;
    }

}
