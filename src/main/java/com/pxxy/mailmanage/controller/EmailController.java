package com.pxxy.mailmanage.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pxxy.mailmanage.entities.ConfigInfo;
import com.pxxy.mailmanage.entities.Email;
import com.pxxy.mailmanage.entities.EmailOwner;
import com.pxxy.mailmanage.service.MailService;
import com.pxxy.mailmanage.utils.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author YZJ
 * @date 2019/4/12 - 10:39
 */
@Controller
public class EmailController {
    @Value("${uploadFile.resourceHandler}")
    private String resourceMapping;
    @Value("${uploadFile.location}")
    private String fileLocation;
    @Autowired
    private MailService mailService;

    /**
     * 登录操作该方法会将当前的邮件发送者的邮箱账号和授权码改为该登录用户的
     * 使得可以切换用户发送邮件，只要你开通了smtp服务并获得授权码
     * @param owner 该系统的邮箱账户拥有者
     * @param request 前台的请求
     * @return
     */
    @GetMapping("/loginIn")
    @ResponseBody
    public String loginIn(EmailOwner owner, HttpServletRequest request){
        ConfigUtil.setProperty("spring_mail_username",owner.getEmailAccount());
        ConfigUtil.setProperty("spring_mail_password",owner.getAuthCode());
        request.getSession().setAttribute("account",owner.getEmailAccount());
        return "登录成功";
    }

    /**
     * 发送邮件
     * @param email 前台发送封装的一个Email对象
     * @param upload 上传的文件对象（MultipartFile）
     * @param request 请求对象
     * @return
     */
    @PostMapping("/mailSend")
    @ResponseBody
    public String sendEmail(Email email, MultipartFile upload, HttpServletRequest request)  {
        email.setSendEmail(ConfigInfo.spring_mail_username);
        email.setSendTime(new Date());
        if(upload.isEmpty()){
            try {
                mailService.sendEmail(email, upload);
            } catch (MessagingException e) {
                return "邮件发送出错!!!";
            }
            return "Ok";
        }else {
            String fileName=upload.getOriginalFilename();
            String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
            String fileServerName=basePath+resourceMapping.substring(0,resourceMapping.lastIndexOf("/")+1)+fileName;
            email.setFileSend(fileName);
            try {
                mailService.sendEmail(email,upload);
            } catch (MessagingException e) {
                return "邮件发送出错!!!";
            }
            File file=new File(fileLocation,fileName);
            try {
                upload.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Ok";
        }
    }

    /**
     * 获取当前账户的所有发送的邮件
     * @param currentPage 页码
     * @return
     */
    @GetMapping("/getMySend")
    @ResponseBody
    public PageInfo mySendEmails(@RequestParam(value="pn",defaultValue="1") int currentPage){
        PageHelper.startPage(currentPage, 6);
        List<Email> emails = mailService.findEmails(ConfigInfo.spring_mail_username);
        PageInfo page = new PageInfo(emails);
        return page;
    }

    /**
     * 删除邮件
     * @param id 邮件的id
     * @return
     */
    @DeleteMapping("/delEmail")
    @ResponseBody
    public String delEmail(@RequestParam(value = "id") Integer id){
        mailService.deleteEmail(id);
        return "ok";
    }
}
