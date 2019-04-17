package com.pxxy.mailmanage.utils;

import com.pxxy.mailmanage.entities.ConfigInfo;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author YZJ
 * @date 2019/4/10 - 22:28
 * 操作mail.properties文件的类
 */
public class ConfigUtil {
    //外部调用该方法对mail.properties文件里的属性进行修改
    public static void setProperty(String properName,String properValue){
        setProperty(ConfigInfo.Mail_PROPERTY,properName,properValue);
    }
    //外部调用该方法来获取mail.properties文件里的属性值
    public static String getProperty(String key){
        return getProperty(ConfigInfo.Mail_PROPERTY,key);
    }

    /**
     * 使用输入输出流设置mail.properties里面的属性值
     * @param filePath 文件路径
     * @param properName 属性名
     * @param properValue 属性值
     */
    public static void setProperty(String filePath,String properName,String properValue){
        Properties properties = new Properties();
        InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(filePath);
        try {
            properties.load(in);
            in.close();
            Object o = properties.setProperty(properName, properValue);
            FileOutputStream outputStream = new FileOutputStream(new File(ConfigUtil.class.getClassLoader().getResource(ConfigInfo.Mail_PROPERTY).toURI()));
            properties.store(outputStream,"");
            outputStream.flush();
            outputStream.close();
            ConfigInfo.initFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用输入流获取mail.properties里面的属性值
     * @param filePath 文件路径
     * @param key 键
     * @return
     * @throws IOException
     */
    public static String getProperty(String filePath,String key) {
        Properties p=new Properties();
        InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(filePath);
        try {
            p.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = p.getProperty(key);
        return s;
    }
}
