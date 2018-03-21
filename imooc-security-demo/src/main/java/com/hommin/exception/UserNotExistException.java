package com.hommin.exception;/**
 * Created by Hommin on 2018/3/6.
 */

/**
 * @author Hommin
 * @ClassName: UserNotExistException
 * @Description: 异常：用户不存在
 * @data 2018年03月06日 下午1:56
 */
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        super("用户不存在");
        id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
