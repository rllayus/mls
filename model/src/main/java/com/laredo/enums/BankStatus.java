package com.laredo.enums;

import lombok.Getter;

@Getter
public enum BankStatus {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");
    private String estado;
    BankStatus(String estado){
        this.estado = estado;
    }
}
