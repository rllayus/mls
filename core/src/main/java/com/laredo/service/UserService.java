package com.laredo.service;

import com.laredo.UserEntity;
import com.laredo.dto.UserRequestDto;
import com.laredo.dto.UserResponseDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    UserResponseDto save(UserRequestDto dto);
    List<UserResponseDto> list();
    UserEntity findUserByUsername(String username);
    CompletableFuture<Integer> methodAsyn();


}
