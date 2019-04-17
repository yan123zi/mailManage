package com.pxxy.mailmanage;

import com.pxxy.mailmanage.entities.ConfigInfo;
import com.pxxy.mailmanage.entities.Email;
import com.pxxy.mailmanage.utils.ConfigUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailmanageApplicationTests {
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    JavaMailSender javaMailSender;
    @Test
    public void contextLoads() throws URISyntaxException {
//        redisTemplate.opsForValue().set("yan","zijiang");
        System.out.println(this.getClass());
        System.out.println(this.getClass().getClassLoader());
        System.out.println(this.getClass().getClassLoader().getResource("mail.properties"));
        System.out.println(this.getClass().getClassLoader().getResource(""));
    }
    @Test
    public void testConfigUtil(){
        String spring_mail_username = ConfigInfo.spring_mail_username;
        System.out.println(spring_mail_username);
        ConfigUtil.setProperty("mail.properties","spring_mail_username","673343330");
        String spring_mail_username1 = ConfigInfo.spring_mail_username;
        System.out.println(spring_mail_username1);
    }
    @Test
    public void testMail() throws MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("3468827556@qq.com");
        helper.setTo("673343330@qq.com");
        helper.setSubject("hello");
        helper.setText("dfsdf");
        File file=new File("E:\\firefox下载\\介绍以及解压密码.txt");
        String fileName=file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("/")+1);
        helper.addAttachment(fileName,file);
        javaMailSender.send(mimeMessage);
    }
    @Test
    public void testJSON(){
        Map<String,String> email=new HashMap<>();
        email.put("a","fwef");
        email.put("fewf","fewf");
        String fwfwefwegrege = JSONObject.wrap(email).toString();
        System.out.println(fwfwefwegrege);
    }
    public String we(){
        Email email = new Email();
        email.setId(1);
        email.setReceiveEmail("fwefwef");
        email.setContent("fegregr");
        email.setFileSend("fegregreg");
        return email.toString();
    }
    @Test
    public void testInfelct() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<? extends MailmanageApplicationTests> aClass = this.getClass();
        Method we = aClass.getMethod("we", null);
        MailmanageApplicationTests tests = aClass.newInstance();
        Object invoke = we.invoke(tests);
        System.out.println(invoke);
    }
}
