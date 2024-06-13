package com.laredo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.laredo.enums.BankStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@Data
public class BankResponseDto implements Serializable {
    @JsonProperty("codigo")
    private short code;
    @JsonProperty("abreviatura")
    private String abbreviation;
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("estado")
    private BankStatus status;
}
