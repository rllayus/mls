package com.laredo.dto.request;

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
public class BankRequestDto implements Serializable {
    private short code;
    private String abbreviation;
    private String name;
    private BankStatus status;
}
