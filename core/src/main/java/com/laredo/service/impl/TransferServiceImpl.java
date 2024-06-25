package com.laredo.service.impl;

import com.laredo.Transaction;
import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.enums.TransactionStatus;
import com.laredo.repository.TransactionRespository;
import com.laredo.service.TransactionService;
import com.laredo.service.TransferService;
import com.laredo.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ClientService clientService;

    @Override
    @Transactional(timeout = 30)
    public TransferResponseDto transfer(TransferRequestDto dto) {
        Transaction tr = new Transaction();
        tr.setOriginAccount(dto.getOriginAccount());
        tr.setOriginName(dto.getOriginName());
        tr.setDestinationAccount(dto.getDestinationAccount());
        tr.setDestinationName(dto.getDestinationName());

        tr.setCodeBankDestination(dto.getCodeBankDestination());
        tr.setCodeBankOrigin(dto.getCodeBankOrigin());
        tr.setAmount(dto.getAmount());
        tr.setDescription(dto.getDescription());
        transactionService.saveTx(tr);


        return TransferResponseDto.builder()
                .status(TransactionStatus.EN_TRANSITO)
                .transactionCode(tr.getId())
                .message("Mejoras de ricardo")
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransferResponseDto verifi(String idTransaccion) {
        return TransferResponseDto.builder()
                .status(TransactionStatus.PROCESADO)
                .transactionCode(idTransaccion)
                .message("Mejoras de ricardo")
                .build();
    }
}
