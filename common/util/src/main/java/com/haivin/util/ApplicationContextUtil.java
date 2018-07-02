package com.haivin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by zr on 2017/6/26.
 */
@Configuration
@Order(0)
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext context = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    
    public static ApplicationContext getContext(){
        return context;
    }
    
    public static <T> T getBean(Class<T> t){
        return context.getBean(t);
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> t){
        return context.getBean(name,t);
    }
}
