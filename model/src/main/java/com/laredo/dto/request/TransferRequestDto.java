package com.laredo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
public class TransferRequestDto implements Serializable {
    @JsonProperty("cuentaOrigen")
    private String originAccount;
    @JsonProperty("nombreOrigen")
    private String originName;
    @JsonProperty("cuentaDestino")
    private String destinationAccount;
    @JsonProperty("nombreDestino")
    private String destinationName;
    @JsonProperty("codigoBancoDestino")
    private short codeBankDestination;
    @JsonProperty("codigoBancoOrigen")
    private short codeBankOrigin;
    @JsonProperty("importe")
    private BigDecimal amount;
    @JsonProperty("glosa")
    private String description;
    @JsonProperty("id_mls")
    private String idMls;
}
