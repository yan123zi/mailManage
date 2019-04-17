package com.pxxy.mailmanage.entities;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author YZJ
 * @date 2019/4/10 - 21:20
 */

/**
 * 该类的属性映射mail.properties里面的各个属性
 */
public class ConfigInfo {
    public static final String Mail_PROPERTY= "mail.properties";
    public static String spring_mail_username;
    public static String spring_mail_password;
    static {
        initFile();
    }

    public static void initFile() {
        Properties properties = new Properties();
        InputStream inputStream = ConfigInfo.class.getClassLoader().getResourceAsStream(Mail_PROPERTY);
        try {
            properties.load(inputStream);
            inputStream.close();
            spring_mail_username=properties.getProperty("spring_mail_username");
            spring_mail_password=properties.getProperty("spring_mail_password");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
