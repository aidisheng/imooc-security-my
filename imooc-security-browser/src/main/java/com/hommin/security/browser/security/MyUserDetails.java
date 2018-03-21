package com.hommin.security.browser.security;/**
 * Created by Hommin on 2018/3/14.
 */

import com.hommin.security.browser.dto.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * @author Hommin
 * @ClassName: MyUserDetails
 * @Description: 自定义用户校验
 * @data 2018年03月14日 上午10:01
 */
public class MyUserDetails implements UserDetails {

    private final String password;
    private final String username;
    private Set<GrantedAuthority> authorities;

    private final User user;

    /**
     * 此处构造器可以传入数据库查询出的实例
     *
      * @param user
     * @param authorities
     */
    public MyUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        if(user.getName() != null && !"".equals(user.getName()) && user.getPass() != null) {
            // 这个时候，username是多少无所谓了，因为密码是用过username查出来的，已经完成了它的任务。
            this.username = user.getName();
            this.password = user.getPass();
            this.user = user;
            this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 具体判断逻辑
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 具体判断逻辑
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 具体判断逻辑
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 具体判断逻辑
        return true;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        TreeSet sortedAuthorities = new TreeSet(new MyUserDetails.AuthorityComparator());
        Iterator var2 = authorities.iterator();

        while(var2.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority)var2.next();
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = 420L;

        private AuthorityComparator() {
        }

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            return g2.getAuthority() == null?-1:(g1.getAuthority() == null?1:g1.getAuthority().compareTo(g2.getAuthority()));
        }
    }
}
