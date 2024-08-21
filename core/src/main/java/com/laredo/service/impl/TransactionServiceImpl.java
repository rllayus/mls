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


        return TransferResponseDto.builder()
                .estado(TransactionStatus.EN_TRANSITO)
                .codigoTransaccion("sadas")
                .mensaje("Mejoras de ricardo")
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
        System.out.println("id mls");
        System.out.println(id);
        System.out.println("al encontrarlo");
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
