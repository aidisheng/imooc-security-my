package com.hommin.web.controller;

/**
 * Created by Hommin on 2018/3/1.
 */

import com.hommin.dto.User;
import com.hommin.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hommin
 * @ClassName: UserController
 * @Description: 用户操作
 * @data 2018年03月01日 下午2:59
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public List<User> user(){
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询对应用户")
    public User getUser(@ApiParam(value="用户id") @PathVariable String id){
        User user = new User();
        user.setId(String.valueOf(id));
        user.setName("Hommin");
        throw new UserNotExistException("User不能为空");
//        return user;
    }

    @PostMapping
    public User creat(@Valid @RequestBody User user){//, BindingResult errors
//        if(errors.hasErrors()){
//            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//        }

        System.out.println(user.getName());
        System.out.println(user.getBirthday());
        System.out.println(user.getPass());

        user.setId("1");
        return user;
    }

}
