package com.laredo.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OKAuthDto implements Serializable {
    private String username;
    private String token;
}
