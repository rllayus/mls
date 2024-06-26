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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

@Service
@Slf4j
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private BankService bankService;
    @Autowired
    private ClientService clientService;

    @Override
    @Transactional(timeout = 30)
    public TransferResponseDto transfer(TransferRequestDto dto)  {
        TransferResponseDto response = new TransferResponseDto();
        Optional<Bank> optionalBank = bankService.findByCode(dto.getCodeBankDestination());
        if (optionalBank.isEmpty()) {
            response.setStatus(TransactionStatus.ERROR);
            response.setMessage("No se encontró el banco destino");
            return response;
        }

        Optional<Bank> optionalBankOrigin = bankService.findByCode(dto.getCodeBankOrigin());
        if (optionalBankOrigin.isEmpty()) {
            response.setStatus(TransactionStatus.ERROR);
            response.setMessage("No se encontró el banco Origen");
            return response;
        }
        log.info("Guardando en base de datos la transaccion con estado en TRANSITO");
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
        tr.setBankDestino(bankDestino);
        tr.setStatus(TransactionStatus.EN_TRANSITO);
        transactionService.saveTx(tr);
        log.info("Transaccion guarado en base de datos");
        String numeroOrdenOriginante = tr.getId();
        dto.setIdMls(tr.getId());
        try {
            TransferResponseDto responseDto = clientService.transfer(bankDestino.getUrl(), bankDestino.getConnectTimeout(),
                    bankDestino.getReadTimeout(), bankDestino.getUser(), bankDestino.getPasswordToApi(), dto);
            responseDto.setTransactionCode(tr.getId());
            transactionService.update(tr.getId(), TransactionStatus.PROCESADO, "Transaccion exitosa");
            return responseDto;
        }catch (ResourceAccessException e) {
            if (e.getMessage() != null && e.getMessage().contains("connect timed out")) {
                log.error("[{}] Se genero error de connectTimeout al invocar el método obtenerEstado Causa: {}", numeroOrdenOriginante, e.getMessage());
            }
            if (e.getMessage() != null && e.getMessage().contains("Read timed out")) {
                log.error("[{}] Se genero error de readTimeout al invocar el método obtenerEstado Causa: {}", numeroOrdenOriginante, e.getMessage());
            } else {
                log.error("[{}] Se genero error de conexion al invocar el método obtenerEstado. ", numeroOrdenOriginante, e);
            }
            transactionService.update(tr.getId(), TransactionStatus.ERROR, "Error al consumir API del Banco");
        }catch (Exception e){
            log.error("[{}] Se genero error generico", numeroOrdenOriginante, e);
            transactionService.update(tr.getId(), TransactionStatus.ERROR, "Error generico consumir API del Banco");
        }

        response.setStatus(TransactionStatus.ERROR);
        response.setMessage("Error en la transacción");
        response.setTransactionCode(numeroOrdenOriginante);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public TransferResponseDto verifi(String idTransaccion) {
        Optional<TransferResponseDto> optional = this.transactionService.findById(idTransaccion);
        if(optional.isEmpty()){
            return optional.get();
        }
        TransferResponseDto response = new TransferResponseDto();
        response.setStatus(TransactionStatus.TRANSACCION_NO_ENCONTRADA);
        response.setMessage("Transaccion no encontrado");
        return response;
    }
}
