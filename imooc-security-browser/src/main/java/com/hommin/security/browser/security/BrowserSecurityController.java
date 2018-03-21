package com.hommin.security.browser.security;/**
 * Created by Hommin on 2018/3/14.
 */

import com.hommin.security.core.properties.SecurityProperties;
import com.hommin.security.core.vo.ResponseResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hommin
 * @ClassName: BrowserSecurityController
 * @Description: browser安全
 * @data 2018年03月14日 下午1:53
 */
@RestController
public class BrowserSecurityController {
    @Autowired
    private SecurityProperties securityProperties;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache cacherequest = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = cacherequest.getRequest(request, response);

        if (savedRequest != null) {
            String requireUrl = savedRequest.getRedirectUrl();
            logger.info("未登录，请求的路径是："+ requireUrl);
            if (StringUtils.endsWithIgnoreCase(requireUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return ResponseResult.build(401, "用户未登录，请引导用户到登录页");
    }
}
