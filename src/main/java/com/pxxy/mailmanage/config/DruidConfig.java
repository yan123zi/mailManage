package com.pxxy.mailmanage.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YZJ
 * @date 2019/4/10 - 18:07
 */
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }
    //配置druid的监控
    //1,配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean StatViewServlet(){
        ServletRegistrationBean<StatViewServlet> statViewServletServletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        HashMap<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","127.0.0.1");//默认就是允许所有访问
        statViewServletServletRegistrationBean.setInitParameters(initParams);
        return statViewServletServletRegistrationBean;
    }
    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean StatViewFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);

        bean.addUrlPatterns("/*");

        return  bean;
        /*FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new StatViewFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        HashMap<String, String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        return filterRegistrationBean;*/
    }
}
