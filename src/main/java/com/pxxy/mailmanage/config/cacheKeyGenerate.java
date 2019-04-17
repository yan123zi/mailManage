package com.pxxy.mailmanage.config;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YZJ
 * @date 2019/4/14 - 20:50
 */
@Component
public class cacheKeyGenerate implements KeyGenerator {
    /**
     * 传入缓存注解标注的方法，对象，参数将其打包成一个hash值作为缓存的key
     * @param o 目标对象
     * @param method 目标方法
     * @param objects 目标方法参数
     * @return
     */
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("target", o.getClass().toGenericString());//放入target的名字
        map.put("method", method.getName());//放入method的名字
        if (objects != null && objects.length > 0) {//把所有参数放进去
            int i = 0;
            for (Object o1 : objects) {
                map.put("params-" + i, o1);
                i++;
            }
        }
        String str = JSONObject.wrap(map).toString();
        byte[] hash = null;
        String s = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        s= MD5Encoder.encode(hash);//使用MD5生成位移key
        return s;
    }
}
