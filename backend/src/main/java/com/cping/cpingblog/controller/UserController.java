package com.cping.cpingblog.controller;


import com.cping.cpingblog.common.lang.Result;
import com.cping.cpingblog.entity.User;
import com.cping.cpingblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Chonghe
 * @since 2021-03-04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public Result index(){
        User user = userService.getById(1L);
        return Result.succ(200, "success", user);
    }
}
