package com.pxxy.mailmanage.mapper;

import com.pxxy.mailmanage.entities.Email;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author YZJ
 * @date 2019/4/12 - 15:24
 */
@Mapper
public interface MailMapper {
    @Insert("insert into oa_email(content,send_email,receive_email,send_time,subject,file_send) values(#{content},#{sendEmail},#{receiveEmail},#{sendTime},#{subject},#{fileSend})")
    public void insertEmail(Email email) ;
    @Select("select * from oa_email where send_email=#{value}")
    public List<Email> selectEmailsBySendMan(String spring_mail_username);
    @Delete("delete from oa_email where id=#{value}")
    public void deleteEmail(Integer id);
}
