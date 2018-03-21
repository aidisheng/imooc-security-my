package com.hommin.security.browser.security;/**
 * Created by Hommin on 2018/3/12.
 */

import com.hommin.security.browser.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author Hommin
 * @ClassName: UserDetailsServiceImpl
 * @Description: 获取用户逻辑
 * @data 2018年03月12日 下午9:48
 */
//@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库根据username 获取对应的user

        // 若找不到对应的user，则抛出UsernameNotFoundException异常

        // 找到，返回给spirng security校验
        //  使用默认提供的
        // return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        // 自定义UserDatils
        String pass = passwordEncoder.encode("123456");
        System.out.println("加密后的密码："+ pass);
        return new MyUserDetails(new User("1", "hommin", pass, Calendar.getInstance().getTime())
                , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
