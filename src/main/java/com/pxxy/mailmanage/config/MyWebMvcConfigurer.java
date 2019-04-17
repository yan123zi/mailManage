package com.pxxy.mailmanage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author YZJ
 * @date 2019/4/12 - 17:39
 * springmvc的额外配置
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Value("${uploadFile.resourceHandler}")
    private String resourceHandler;//请求 url 中的资源映射，不推荐写死在代码中，最好提供可配置，如 /uploadFiles/**
    @Value("${uploadFile.location}")
    private String location;//上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值，如 F:/test/uploadFiles
    /**
     * 配置静态资源映射
     * 添加静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //就是说 url 中出现 resourceHandler 匹配时，则映射到 location 中去,location 相当于虚拟路径
        //映射本地文件时，开头必须是 file:/// 开头，表示协议
        registry.addResourceHandler(resourceHandler).addResourceLocations("file:///"+location);
        //必须添加下面的static的静态路径，否则
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    /*<mvc:default-servlet-handler/>*/

    /**
     * 配置映射路径，添加Controller映射路径
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/component/login").setViewName("component/login");
        registry.addViewController("/component/index").setViewName("component/index");
        registry.addViewController("/component/mySend").setViewName("component/mySend");
    }
}
