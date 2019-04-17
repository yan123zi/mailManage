package com.pxxy.mailmanage.service;

import com.pxxy.mailmanage.entities.Email;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;

/**
 * @author YZJ
 * @date 2019/4/12 - 15:24
 */
public interface MailService {
    /**
     * 发送邮件
     * @param email 邮件对象
     * @param upload 上传的文件对象
     * @throws MessagingException
     */
    public void sendEmail(Email email, MultipartFile upload) throws MessagingException;

    /**
     * 查询所有邮件
     * @param spring_mail_username 当前用户的账户号
     * @return
     */
    public List<Email> findEmails(String spring_mail_username);

    /**
     * 根据id删除邮件
     * @param id 邮件的id
     */
    public void deleteEmail(Integer id);
}
