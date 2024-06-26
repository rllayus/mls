package com.laredo.service.impl;

import com.laredo.Transaction;
import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.enums.TransactionStatus;
import com.laredo.repository.TransactionRespository;
import com.laredo.service.TransactionService;
import com.laredo.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRespository transactionRespository;

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
        transactionRespository.save(tr);



        tr.setDescription("asdasdasdasdas");
        this.transactionRespository.save(tr);

        return TransferResponseDto.builder()
                .status(TransactionStatus.EN_TRANSITO)
                .transactionCode(tr.getId())
                .message("Mejoras de ricardo")
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveTx(Transaction transaction) {
        this.transactionRespository.save(transaction);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(String id, TransactionStatus status, String mensaje) {
        this.transactionRespository.updateStatus(id, status, mensaje);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransferResponseDto> findById(String id) {
        return this.transactionRespository.obtenerTransaccion(id);
    }

    private void sleep(){
        try {
            Thread.sleep(90000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
