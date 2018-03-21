package com.hommin.web.controller;/**
 * Created by Hommin on 2018/3/9.
 */

import com.hommin.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hommin
 * @ClassName: TestController
 * @Description: 测试类
 * @data 2018年03月09日 上午8:48
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public User getUserTest(){
        System.out.println("进入test服务");
        return new User();
    }
}
