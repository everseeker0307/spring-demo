package com.everseeker.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by everseeker on 16/8/7.
 */
public class IOC {
    private static ApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);

    public static Object getBean(String beanname) {
        return context.getBean(beanname);
    }
}
