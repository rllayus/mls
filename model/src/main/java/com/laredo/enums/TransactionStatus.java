package com.laredo.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    PENDIENTE,
    EN_TRANSITO,
    PROCESADO,
    ERROR, TRANSACCION_NO_ENCONTRADA;

}
