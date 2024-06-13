package com.laredo.service;

import com.laredo.dto.response.BankResponseDto;

import java.util.List;

public interface BankService {
    List<BankResponseDto> listAll();
}
