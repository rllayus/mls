package com.laredo.mls.controller;

import com.laredo.dto.UserResponseDto;
import com.laredo.dto.response.BankResponseDto;
import com.laredo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bancos")
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping()
    public ResponseEntity<List<BankResponseDto>> getAll() {
        return ResponseEntity.ok(bankService.listAll());
    }
}
