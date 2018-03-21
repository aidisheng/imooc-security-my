package com.hommin.web;/**
 * Created by Hommin on 2018/3/6.
 */

import com.hommin.web.filter.TimeFilter;
import com.hommin.web.interceptor.TimeInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hommin
 * @ClassName: WebConfig
 * @Description: web配置
 * @data 2018年03月06日 下午2:32
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
//        configurer.
    }

    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        List<String > urls = new ArrayList<>();
        urls.add("/*");

        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        registry.addInterceptor(new TimeInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/login");
    }
}
