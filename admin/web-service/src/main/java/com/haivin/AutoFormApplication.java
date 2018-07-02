package com.haivin;

import com.baomidou.mybatisplus.activerecord.Model;
import com.haivin.database.activerecord.EaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author: zr
 * @Date: 2018/1/29 16:24
 */
@SpringBootApplication
public class AutoFormApplication {
    static Logger logger = LoggerFactory.getLogger(AutoFormApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AutoFormApplication.class, args);
//        String[] beanNames = ctx.getBeanDefinitionNames();
        /*String[] beanNames = ctx.getBeanNamesForType(EaModel.class);
        logger.info("所以beanNames个数：{}", beanNames.length);
        for (String bn : beanNames) {
            logger.info(bn);
            EaModel model = ctx.getBean(bn, EaModel.class);
            logger.info("model ====>{}", model);
            List<Map<String,Object>> lists = model.getEaColumnInfo();
            //遍历
            for (Map<String,Object> maps : lists) {
                for (Map.Entry<String,Object> entry:maps.entrySet()) {
                    logger.info("key:{},value:{}",entry.getKey(),entry.getValue());
                }
            }
        }*/
    }

    /*@Bean
    public ScriptFactoryPostProcessor getScriptFactoryPostProcessor(){
        ScriptFactoryPostProcessor scriptFactoryPostProcessor = new ScriptFactoryPostProcessor();
        return scriptFactoryPostProcessor;
    }*/
}
