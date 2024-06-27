package com.laredo.service.impl;

import com.laredo.UserEntity;
import com.laredo.dto.UserRequestDto;
import com.laredo.dto.UserResponseDto;
import com.laredo.repository.UserRespository;
import com.laredo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {
    @Autowired

    private UserRespository userRespository;

    @Override
    public UserResponseDto save(UserRequestDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(dto.getUserName());
        userEntity.setName(dto.getName());
        userEntity.setEstadoUsuario(dto.getEstadoUsuario());
        return new UserResponseDto(this.userRespository.save(userEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> list() {
        return userRespository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findUserByUsername(String username) {
        return this.userRespository.findByUserName(username).get();
    }

    @Override
    @Async
    public CompletableFuture<Integer> methodAsyn() {
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Ya me ejecute Feliz cumple Cami!!!!!");
        return CompletableFuture.completedFuture(0);
    }
}
