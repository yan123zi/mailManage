package com.pxxy.mailmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:mailConfig.xml")
@EnableCaching
@SpringBootApplication
public class MailmanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailmanageApplication.class, args);
    }

}
