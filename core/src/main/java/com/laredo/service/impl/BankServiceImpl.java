package com.laredo.service.impl;

import com.laredo.Bank;
import com.laredo.dto.response.BankResponseDto;
import com.laredo.enums.BankStatus;
import com.laredo.repository.BankRespository;
import com.laredo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRespository bankRespository;
    @Override
    @Transactional(readOnly = true)
    public List<BankResponseDto> listAll() {
        return bankRespository.findByStatus(BankStatus.ACTIVO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<Bank> findByCode(Short id) {
        return bankRespository.findByCode(id);
    }
}
