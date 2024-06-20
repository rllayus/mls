package com.laredo.service.client;

import com.laredo.dto.AuthenticationDto;
import com.laredo.dto.OKAuthDto;
import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;

public interface ClientService {
    OKAuthDto login(AuthenticationDto okAuthDto);
    TransferResponseDto transfer(String jwt, TransferRequestDto dto);
}
