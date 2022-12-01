package com.hieunt.usermanage.controller;

import com.hieunt.usermanage.entity.User;
import com.hieunt.usermanage.payload.UserResponse;
import com.hieunt.usermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    // GUI - Giao dien
    @GetMapping({"/", "/home"})
    public String home() {
        return "index";
    }

    // API - doc ghi du lieu
    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody User request) {
        User response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api/users")
    public ResponseEntity<UserResponse> getUser(@RequestParam (required = false) String search ,
                                                @RequestParam int page , @RequestParam int size) {
        UserResponse response = (UserResponse) userService.getUser(search , page - 1 , size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/users")
    public ResponseEntity<User> updateUser(@RequestBody User request) {
        User response = userService.updateUser(request);
        if (response == null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User response = userService.getUserById(id);
        if (response == null) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
