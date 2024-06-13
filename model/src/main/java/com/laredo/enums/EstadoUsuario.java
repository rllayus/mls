package com.laredo.enums;

import lombok.Getter;

@Getter
public enum EstadoUsuario {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    BLOQUEADO("Bloqueado");// SUSPENDIDO
    private String estado;
    EstadoUsuario(String estado){
        this.estado = estado;
    }
}
