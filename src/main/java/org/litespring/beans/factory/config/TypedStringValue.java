package org.litespring.beans.factory.config;


/**
 * TypedStringValue保存的是一个类型的属性值
 *
 * @author yunfy
 * @create 2018-12-11 23:22
 **/
public class TypedStringValue {
    private String value;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
