package com.laredo.repository;

import com.laredo.UserEntity;
import com.laredo.dto.UserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u from UserEntity u ")
    List<UserResponseDto> listar();

    @Query("SELECT new com.laredo.dto.UserResponseDto(u.id,u.name, u.userName, u.estadoUsuario) from UserEntity u ")
    List<UserResponseDto> listar2();

    Optional<UserEntity> findByUserName(String username);
}
