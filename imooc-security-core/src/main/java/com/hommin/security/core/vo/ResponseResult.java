package com.hommin.security.core.vo;/**
 * Created by Hommin on 2018/3/8.
 */

/**
 * @author Hommin
 * @ClassName: ResponseResult
 * @Description: 返回json格式数据
 * @data 2018年03月08日 下午4:50
 */
public class ResponseResult {
    /**
     * 响应状态码
      */
    private Integer status;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应中的数据
     */
    private Object data;

    public static ResponseResult build(Integer status, String msg, Object data) {
        return new ResponseResult(status, msg, data);
    }

    public static ResponseResult build(Integer status, String msg) {
        return build(status, msg, null);
    }

    public static ResponseResult ok(String msg, Object data) {
        return build(200, msg, data);
    }

    public static ResponseResult ok(Object data) {
        return ok("success", data);
    }

    public static ResponseResult ok(String msg) {
        return ok(msg, null);
    }

    public static ResponseResult ok() {
        return ok("success", null);
    }

    public ResponseResult() {}

    public ResponseResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
