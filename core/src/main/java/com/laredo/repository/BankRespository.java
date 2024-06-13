package com.laredo.repository;

import com.laredo.Bank;
import com.laredo.dto.response.BankResponseDto;
import com.laredo.enums.BankStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRespository extends JpaRepository<Bank, String> {
    List<BankResponseDto> findByStatus(BankStatus status);
}
