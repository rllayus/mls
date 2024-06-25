package com.laredo.repository;

import com.laredo.Bank;
import com.laredo.dto.response.BankResponseDto;
import com.laredo.enums.BankStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankRespository extends JpaRepository<Bank, String> {
    List<BankResponseDto> findByStatus(BankStatus status);
    Optional<Bank> findByCode(Short code);
}
