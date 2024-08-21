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
import java.util.UUID;

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

        Optional<Bank> optionalBank = bankService.findByCode(dto.getCodigoBancoDestino());
        if (optionalBank.isEmpty()) {
            response.setEstado(TransactionStatus.ERROR);
            response.setMensaje("No se encontró el banco destino");
            return response;
        }

        Optional<Bank> optionalBankOrigin = bankService.findByCode(dto.getCodigoBancoOrigen());
        if (optionalBankOrigin.isEmpty()) {
            response.setEstado(TransactionStatus.ERROR);
            response.setMensaje("No se encontró el banco Origen");
            return response;
        }

        log.info("Guardando en base de datos la transaccion con estado en TRANSITO");
        Bank bankDestino = optionalBank.get();
        Transaction tr = new Transaction();
        tr.setOriginAccount(dto.getCuentaOrigen());
        tr.setOriginName(dto.getNombreOrigen());
        tr.setDestinationAccount(dto.getCuentaDestino());
        tr.setDestinationName(dto.getNombreDestino());

        tr.setCodeBankDestination(dto.getCodigoBancoDestino());
        tr.setCodeBankOrigin(dto.getCodigoBancoOrigen());
        tr.setAmount(dto.getImporte());
        tr.setDescription(dto.getGlosa());
        tr.setBankDestino(bankDestino);
        tr.setBankOrigen(optionalBank.get());
        tr.setStatus(TransactionStatus.EN_TRANSITO);
        transactionService.saveTx(tr);


        log.info("Transaccion guardado en base de datos");
        String numeroOrdenOriginante = tr.getId();
        dto.setIdMls(tr.getId());

        try {
            TransferResponseDto responseDto = clientService.transfer(bankDestino.getUrl(), bankDestino.getConnectTimeout(),
                    bankDestino.getReadTimeout(), bankDestino.getUser(), bankDestino.getPasswordToApi(), dto);
            responseDto.setCodigoTransaccion(tr.getId());
            if(responseDto.getEstado().equals(TransactionStatus.PROCESADO)) {
                transactionService.update(tr.getId(), TransactionStatus.PROCESADO, responseDto.getMensaje());
            }else {
                transactionService.update(tr.getId(), TransactionStatus.ERROR, responseDto.getMensaje());
            }
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

        response.setEstado(TransactionStatus.ERROR);
        response.setMensaje("Error en la transacción");
        response.setCodigoTransaccion(numeroOrdenOriginante);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public TransferResponseDto verifi(String idTransaccion) {
        Optional<TransferResponseDto> optional = this.transactionService.findById(idTransaccion);
        if(!optional.isEmpty()){
            return optional.get();
        }
        TransferResponseDto response = new TransferResponseDto();
        response.setEstado(TransactionStatus.TRANSACCION_NO_ENCONTRADA);
        response.setMensaje("Transaccion no encontrado");
        return response;
    }
}
