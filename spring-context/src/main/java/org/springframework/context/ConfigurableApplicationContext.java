package org.springframework.context;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.io.Closeable;

public interface ConfigurableApplicationContext extends ApplicationContext, Lifecycle, Closeable {

    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
}
