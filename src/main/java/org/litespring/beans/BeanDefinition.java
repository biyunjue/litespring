package org.litespring.beans;

import java.util.List;

/**
 * @author yunfy
 * @create 2018-11-15 22:53
 **/
public interface BeanDefinition {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    String SCOPE_DEFAULT = "";

    /**
     * 获取bean的ID
     *
     * @return
     */
    String getID();

    /**
     * 获取bean的类名称
     *
     * @return
     */
    String getBeanClassName();

    /**
     * 是否单例
     *
     * @return
     */
    boolean isSingleton();

    /**
     * 是否多个实例
     *
     * @return
     */
    boolean isPrototype();

    /**
     * 获取实例创建的配置
     *
     * @return
     */
    String getScope();

    /**
     * 设置实例创建的配置
     *
     * @param scope
     */
    void setScope(String scope);

    /**
     * 获取bean的属性集合
     *
     * @return
     */
    List<PropertyValue> getPropertyValues();

    /**
     * bean构造属性集合
     *
     * @return
     */
    ConstructorArgument getConstructorArgument();


    /**
     * 判断是否有构造属性
     *
     * @return
     */
    boolean hasConstructorArgumentValues();
}
