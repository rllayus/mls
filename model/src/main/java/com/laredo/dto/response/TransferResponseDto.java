package com.laredo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laredo.Transaction;
import com.laredo.enums.TransactionStatus;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferResponseDto implements Serializable {
    //@JsonProperty("codigoTransaccion")
    private String codigoTransaccion;
   // @JsonProperty("estado")
    private TransactionStatus estado;
    //@JsonProperty("mensaje")
    private String mensaje;


    public TransferResponseDto(Transaction t){
        System.out.println("id encontrado");
        System.out.println(t.getId());
        System.out.println(t.getStatus());

        this.setEstado(t.getStatus());
        this.setCodigoTransaccion(t.getId());
        this.setMensaje(t.getMessage());
    }


}
