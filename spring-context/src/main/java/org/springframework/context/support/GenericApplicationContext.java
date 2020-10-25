package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.ResolvableType;

import java.io.IOException;

public class GenericApplicationContext extends AbstractApplicationContext implements BeanDefinitionRegistry {
    public void close() throws IOException {

    }

    public Object getBean(String name) throws BeansException {
        return null;
    }

    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }

    public Object getBean(String name, Object... args) throws BeansException {
        return null;
    }

    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return null;
    }

    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        return null;
    }

    public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
        return null;
    }

    public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType) {
        return null;
    }

    public boolean containsBean(String name) {
        return false;
    }

    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    public boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException {
        return false;
    }

    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
        return false;
    }

    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return null;
    }

    public Class<?> getType(String name, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException {
        return null;
    }

    public String[] getAliases(String name) {
        return new String[0];
    }
}
