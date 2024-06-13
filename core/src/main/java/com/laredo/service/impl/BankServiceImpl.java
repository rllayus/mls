package com.laredo.service.impl;

import com.laredo.dto.response.BankResponseDto;
import com.laredo.enums.BankStatus;
import com.laredo.repository.BankRespository;
import com.laredo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRespository bankRespository;
    @Override
    @Transactional(readOnly = true)
    public List<BankResponseDto> listAll() {
        return bankRespository.findByStatus(BankStatus.ACTIVO);
    }
}
