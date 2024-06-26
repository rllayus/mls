package com.laredo.service;

import com.laredo.Transaction;
import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.enums.TransactionStatus;

import java.util.Optional;

public interface TransactionService {
    TransferResponseDto transfer(TransferRequestDto dto);
    void saveTx(Transaction transaction);
    void update(String id, TransactionStatus status, String mensaje);
    Optional<TransferResponseDto> findById(String id);
}
