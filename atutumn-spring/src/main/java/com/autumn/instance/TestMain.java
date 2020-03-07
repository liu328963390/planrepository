package com.autumn.instance;

import com.autumn.instance.config.MainCofig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
//        Persion person = (Persion) applicationContext.getBean("person");
//        System.out.println(person);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainCofig.class);
        Persion bean = applicationContext.getBean(Persion.class);
        System.out.println(bean);
    }
}
