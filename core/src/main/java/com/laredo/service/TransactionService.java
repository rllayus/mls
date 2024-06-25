package com.laredo.service;

import com.laredo.Transaction;
import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.enums.TransactionStatus;

public interface TransactionService {
    TransferResponseDto transfer(TransferRequestDto dto);
    void saveTx(Transaction transaction);
    void update(Transaction transaction, TransactionStatus status);
}
