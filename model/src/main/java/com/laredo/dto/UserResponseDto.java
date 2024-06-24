package com.laredo.dto;

import com.laredo.enums.EstadoUsuario;
import com.laredo.UserEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public class UserResponseDto implements Serializable {
    private String id;
    private String name;
    private String userName;
    private String estadoUsuario;

    public UserResponseDto(UserEntity userEntity){
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.userName = userEntity.getUsername();
        this.estadoUsuario = userEntity.getEstadoUsuario().getEstado();
    }

    public UserResponseDto(String id, String name, String userName, EstadoUsuario estadoUsuario) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.estadoUsuario = estadoUsuario.name();
    }
}
