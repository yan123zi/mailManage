package com.pxxy.mailmanage.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author YZJ
 * @date 2019/4/10 - 20:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailOwner {
    /**
     * 邮箱账号
     */
    private String emailAccount;
    /**
     * 授权码
     */
    private String authCode;
}
