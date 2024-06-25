package com.laredo.service;

import com.laredo.Bank;
import com.laredo.dto.response.BankResponseDto;

import java.util.List;
import java.util.Optional;

public interface BankService {
    List<BankResponseDto> listAll();
    Optional<Bank> findByCode(Short id);
}
