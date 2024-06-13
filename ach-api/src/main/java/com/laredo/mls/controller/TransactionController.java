package com.laredo.mls.controller;

import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.BankResponseDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/transferencia")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransferResponseDto> transfer(
            @RequestBody TransferRequestDto dto) {

        try {
            return ResponseEntity.ok(transactionService.transfer(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }


}
