package com.anibaba.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/test")
    public String loginSuccess() {
        System.out.println("访问资源成功");
        return "登录成功";
    }

}
