package com.hommin.security.browser.security.authentication;/**
 * Created by Hommin on 2018/3/16.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hommin.security.core.properties.LoginType;
import com.hommin.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hommin
 * @ClassName: MyLoginSuccessHandler
 * @Description: 登录成功后处理
 * @data 2018年03月16日 上午8:37
 */
@Component("myLoginSuccessHandler")
public class MyLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
    /**
     * 判断该登录请求时ajax请求返回json数据，还是html的form提交。
     */
}
