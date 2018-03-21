package com.hommin.security.core.properties;/**
 * Created by Hommin on 2018/3/14.
 */

/**
 * @author Hommin
 * @ClassName: BrowserProperties
 * @Description: browser配置文件
 * @data 2018年03月14日 下午2:20
 */
public class BrowserProperties {

    private String loginPage = "/imooc-login.html";

    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
