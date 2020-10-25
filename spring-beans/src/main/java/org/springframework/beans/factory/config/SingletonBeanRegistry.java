package org.springframework.beans.factory.config;


import org.springframework.lang.Nullable;

/**
 * Interface that defines a registry for shared bean instances.
 * Can be implemented by {@link org.springframework.beans.factory.BeanFactory}
 * implementations in order to expose their singleton management facility
 * in a uniform manner.
 *
 * <p>The {@link ConfigurableBeanFactory} interface extends this interface.
 *
 * @author Juergen Hoeller
 * @since 2.0
 * @see ConfigurableBeanFactory
 * @see org.springframework.beans.factory.support.DefaultSingletonBeanRegistry
 * @see org.springframework.beans.factory.support.AbstractBeanFactory
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);


    @Nullable
    Object getSingleton(String beanName);


    boolean containsSingleton(String beanName);


    String[] getSingletonNames();


    int getSingletonCount();


    Object getSingletonMutex();
}
