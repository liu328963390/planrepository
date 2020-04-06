package com.big.data.businessmanageweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.big.data")
public class BusinessManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessManageWebApplication.class, args);
    }

}
