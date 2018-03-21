package com.hommin.dto;/**
 * Created by Hommin on 2018/3/1.
 */

import com.fasterxml.jackson.annotation.JsonView;
import com.hommin.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author Hommin
 * @ClassName: User
 * @Description: 用户
 * @data 2018年03月01日 下午3:01
 */
public class User {

    public interface UserSimpleView{};

    public interface UserDetailView extends UserSimpleView{};

    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户姓名")
    @MyConstraint(message = "请输入参数：Hommin")
    private String name;

    @ApiModelProperty("用户密码")
    @NotBlank(message = "密码不能为空")
    private String pass;

    @ApiModelProperty("用户生日")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(UserDetailView.class)
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User(String id, String name, String pass, Date birthday) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.birthday = birthday;
    }

    public User() {
    }
}
