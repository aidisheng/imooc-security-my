package com.hommin.web.exceptionhandler;/**
 * Created by Hommin on 2018/3/6.
 */

import com.hommin.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hommin
 * @ClassName: ControllerExcetionHandler
 * @Description: 控制层异常处理器
 * @data 2018年03月06日 下午1:58
 */
@ControllerAdvice
public class ControllerExcetionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    public Map<String, Object> handlerUserNotExistException(UserNotExistException ex){
        Map<String, Object> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }
}
