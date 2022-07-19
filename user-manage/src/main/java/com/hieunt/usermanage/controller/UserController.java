package com.hieunt.usermanage.controller;

import com.hieunt.usermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping({"/", "/home"})
    public String home() {

        return "index";
    }
}
