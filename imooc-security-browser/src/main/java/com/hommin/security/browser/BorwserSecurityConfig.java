package com.hommin.security.browser;/**
 * Created by Hommin on 2018/3/12.
 */

import com.hommin.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Hommin
 * @ClassName: BorwserSecurityConfig
 * @Description: springsecurity配置类
 * @data 2018年03月12日 下午8:45
 */
@Configuration
public class BorwserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler myLoginSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myLoginFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .httpBasic()
                .formLogin()
                    // 登录页面
                    .loginPage("/authentication/require")
                    // 登录请求处理路径
                    .loginProcessingUrl("/authentication/form")
                    // 登录成功后处理
                    .successHandler(myLoginSuccessHandler)
                    // 登录失败后处理
                    .failureHandler(myLoginFailureHandler)

                .and()
                .authorizeRequests()

                .antMatchers("/authentication/require"
                            ,securityProperties.getBrowser().getLoginPage()
                            , "/code/image"
                        ).permitAll()

                .anyRequest()
                .authenticated()

                // 暂时不用csrf登录防护
                .and()
                .csrf().disable();
    }

    /**
     * spring security登录功能小结：
     *  1. 自定义登录页面及登录页面后处理登录请求的url
     *      1.1 未登录时拦截的请求一般有两种方式：一种json请求，该请求应该返回json字符串提示前台自己跳转登录页面；一种直接跳转登录页面。因此拦截后不应该跳转页面，而是跳转到controller中进行判断，然后根据结果返回。
     *      1.2 配置哪些路径不被登录拦截
     *  2. 自定义用户认证方式：
     *      2.1 根据username去数据库查询user
     *      2.2 根据user进行可用判断
     *      2.3 加密问题
     *  3. 灵活配置自定义登录页面（将其放入配置文件并读取）
     *  4. 登录成功/失败后的处理
     *      4.1 如果登录请求是ajax提交的，则返回json对象
     *      4.2 如果登录请求是html的form提交的，则返回相应页面
     *
     *
     */
}
