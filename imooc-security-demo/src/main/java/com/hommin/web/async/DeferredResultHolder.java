package com.hommin.web.async;/**
 * Created by Hommin on 2018/3/11.
 */

import com.hommin.dto.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hommin
 * @ClassName: DefferedResultHolder
 * @Description: 异步延迟处理
 * @data 2018年03月11日 下午6:11
 */
@Component
public class DeferredResultHolder {

    private Map<String, DeferredResult<User>> map = new HashMap<String, DeferredResult<User>>();

    public Map<String, DeferredResult<User>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<User>> map) {
        this.map = map;
    }
}
