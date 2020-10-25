package org.springframework.context.annotation;

import org.springframework.context.support.GenericApplicationContext;

public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {
    public AnnotationConfigApplicationContext() {
        super(beanFactory);
    }
}
