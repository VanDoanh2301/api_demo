package com.example.warehouses.controller;

import com.example.warehouses.model.domain.User;
import com.example.warehouses.model.dto.UserDto;
import com.example.warehouses.model.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("insertUser")
    private ResponseEntity<?> insertUser(@RequestBody UserDto userDto) {
        if(!isValidEmail(userDto.getEmail())) {
            return ResponseEntity.ok("Invalid email address");
        }
        if(userService.existsByUserId(userDto.getUserId())) {
            return ResponseEntity.ok("User Id is already exist");
        }
        if(StringUtils.isBlank(String.valueOf(userDto.getName()))) {
            return ResponseEntity.ok("Name is required");
        }
        if(userService.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.ok("Email is already exist");
        }
        if(StringUtils.isBlank(String.valueOf(userDto.getName()))) {
            return ResponseEntity.ok("Name is required");
        }
        if(StringUtils.isBlank(String.valueOf(userDto.getUserId()))) {
            return ResponseEntity.ok("User Id is required");
        }
        if(StringUtils.isBlank(String.valueOf(userDto.getEmail()))) {
            return ResponseEntity.ok("Email  is required");
        }
        if(StringUtils.isBlank(String.valueOf(userDto.getAvatar()))) {
            return ResponseEntity.ok("Avatar  is required");
        }

        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userService.save(user);
        return ResponseEntity.ok("Save user success");
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
 }
