package com.autumn.instance.config;

import com.autumn.instance.Persion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类==配置文件
 */
@Configuration  //这是一个配置类
public class MainCofig {

    /**
     * 给容器中注册一个bean；类型为返回值的类型，id默认是用方法名为id
     * @return
     */
    @Bean
    public Persion persion(){
        return new Persion("",89,34.2,43.4);
    }
}
