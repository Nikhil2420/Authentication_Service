package com.UserService.backend.controller;

import com.UserService.backend.entity.User;
import com.UserService.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        return userService.registerUser(user);
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        return userService.loginUser(username, password);

    }


}
