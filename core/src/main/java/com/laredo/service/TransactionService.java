package com.laredo.service;

import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;

public interface TransactionService {
    TransferResponseDto transfer(TransferRequestDto dto);
}
