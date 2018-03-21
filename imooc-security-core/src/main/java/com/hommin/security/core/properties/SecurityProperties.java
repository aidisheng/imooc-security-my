package com.hommin.security.core.properties;/**
 * Created by Hommin on 2018/3/14.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Hommin
 * @ClassName: SecurityProperties
 * @Description: spring security的配置文件
 * @data 2018年03月14日 下午2:18
 */
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
