package com.laredo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferRequestDto implements Serializable {
    private String cuentaOrigen;
    private String nombreOrigen;
    private String cuentaDestino;
    private String nombreDestino;
    private short codigoBancoDestino;
    private short codigoBancoOrigen;
    private BigDecimal importe;
    private String glosa;
    private String idMls;
}
