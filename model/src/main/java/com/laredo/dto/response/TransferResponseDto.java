package com.laredo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laredo.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDto implements Serializable {
    @JsonProperty("codigoTransaccion")
    private String transactionCode;
    @JsonProperty("estado")
    private TransactionStatus status;
    @JsonProperty("mensaje")
    private String message;
}
