package com.pxxy.mailmanage.service.impl;

import com.pxxy.mailmanage.entities.ConfigInfo;
import com.pxxy.mailmanage.entities.Email;
import com.pxxy.mailmanage.mapper.MailMapper;
import com.pxxy.mailmanage.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @author YZJ
 * @date 2019/4/12 - 15:25
 */
@Service
public class EmailServiceImpl implements MailService {
    @Autowired
    private MailMapper mailMapper;
    /**
     * 邮件发送对象
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送邮件
     * @param email 邮件对象
     * @param upload 上传的文件对象
     * @throws MessagingException
     */
    @Override
    public void sendEmail(Email email, MultipartFile upload) throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true);
        messageHelper.setFrom(email.getSendEmail());
        messageHelper.setTo(email.getReceiveEmail());
        messageHelper.setSubject(email.getSubject());
        messageHelper.setText(email.getContent());
        if(upload.isEmpty()){
            mailSender.send(mimeMessage);
        }else{
            String filename = upload.getOriginalFilename();
            messageHelper.addAttachment(filename,upload);
            mailSender.send(mimeMessage);
        }
        mailMapper.insertEmail(email);
    }

    /**
     * 根据当前账号查询邮件
     * @param spring_mail_username 当前用户的账户号
     * @return
     */
    @Override
    public List<Email> findEmails(String spring_mail_username) {
        List<Email> emails = mailMapper.selectEmailsBySendMan(spring_mail_username);
        return emails;
    }

    /**
     * 根据邮件id删除邮件
     * @param id 邮件的id
     */
    @Override
    public void deleteEmail(Integer id) {
        mailMapper.deleteEmail(id);
    }
}
