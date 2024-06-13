package com.laredo.mls.controller;

import com.laredo.dto.UserResponseDto;
import com.laredo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        //List<UserResponseDto> list = userService.list();
        return ResponseEntity.ok(userService.list());
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsersv2() {
        //List<UserResponseDto> list = userService.list();
        return ResponseEntity.ok(userService.list());
    }

}
