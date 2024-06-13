package com.laredo.dto;

import com.laredo.enums.EstadoUsuario;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserRequestDto implements Serializable {
    private String name;
    private String userName;
    private EstadoUsuario estadoUsuario;
}
