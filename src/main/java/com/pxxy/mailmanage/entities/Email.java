package com.pxxy.mailmanage.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * @author YZJ
 * @date 2019/4/10 - 20:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Email implements Serializable {
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 邮箱发送者的email
     */
    private String sendEmail;
    /**
     * 邮箱接收者的email
     */
    private String receiveEmail;
    /**
     * 邮件发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date sendTime;
    /**
     * 邮件的主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 附件
     */
    private String fileSend;

}
