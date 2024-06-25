package com.laredo.service.impl;

import com.laredo.Bank;
import com.laredo.Transaction;
import com.laredo.dto.AuthenticationDto;
import com.laredo.dto.OKAuthDto;
import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.enums.TransactionStatus;
import com.laredo.repository.TransactionRespository;
import com.laredo.service.BankService;
import com.laredo.service.TransactionService;
import com.laredo.service.TransferService;
import com.laredo.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private BankService bankService;
    @Autowired
    private ClientService clientService;

    @Override
    @Transactional(timeout = 30)
    public TransferResponseDto transfer(TransferRequestDto dto) throws Exception {
        Optional<Bank> optionalBank = bankService.findByCode(dto.getCodeBankDestination());
        if(optionalBank.isEmpty()){
            throw new Exception("dsadsadasad");
        }
        Bank bankDestino = optionalBank.get();
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
        dto.setIdMls(tr.getId());
        AuthenticationDto authenticationDto = new AuthenticationDto();
        authenticationDto.setUsername(bankDestino.getUser());
        authenticationDto.setPassword(bankDestino.getPasswordToApi());

        OKAuthDto okAuthDto = clientService.login(bankDestino.getUrl(), bankDestino.getConnectTimeout(), bankDestino.getReadTimeout(), authenticationDto);

        TransferResponseDto responseDto = clientService.transfer(bankDestino.getUrl(), bankDestino.getConnectTimeout(), bankDestino.getReadTimeout(), okAuthDto.getToken(),dto );

        responseDto.setTransactionCode(tr.getId());
        return responseDto;
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
