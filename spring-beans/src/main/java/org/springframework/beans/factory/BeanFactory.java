package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

public interface BeanFactory {


    String FACTORY_BEAN_PREFIX = "&";



    Object getBean(String name) throws BeansException;


    <T> T getBean(String name, Class<T> requiredType) throws BeansException;


    Object getBean(String name, Object... args) throws BeansException;


    <T> T getBean(Class<T> requiredType) throws BeansException;


    <T> T getBean(Class<T> requiredType, Object... args) throws BeansException;


    <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType);


    <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType);


    boolean containsBean(String name);


    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;


    boolean isPrototype(String name) throws NoSuchBeanDefinitionException;


    boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException;


    boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException;


    @Nullable
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;


    @Nullable
    Class<?> getType(String name, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException;


    String[] getAliases(String name);
}
