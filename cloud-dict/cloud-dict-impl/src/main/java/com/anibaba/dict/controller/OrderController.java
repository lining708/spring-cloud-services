package com.anibaba.dict.controller;

import com.anibaba.dict.model.UserDTO;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/fun1")
    @PreAuthorize("hasAnyAuthority('fun1')")
    public String call1() {
        UserDTO userDTO = (UserDTO) SecurityContextHolder.getContext().getAuthentication();
        return userDTO.getUsername() + "--访问资源";
    }

    @GetMapping("/fun4")
    @PostAuthorize("hasAnyAuthority('fun4')")
    public String call3() {
        System.out.println("方法fun4      ->      调用成功");
        return "success";
    }


}
