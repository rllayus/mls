package com.laredo.repository;

import com.laredo.Transaction;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.enums.TransactionStatus;
import jakarta.annotation.security.PermitAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRespository extends JpaRepository<Transaction, String> {
    @Modifying
    @Query("UPDATE Transaction t set t.status =:pStatus, t.message =:pMessage where t.id =:pId")
    void updateStatus(@Param("pId") String pId, @Param("pStatus") TransactionStatus pStatus, @Param("pMessage") String pMessage);
    @Query("SELECT new com.laredo.dto.response.TransferResponseDto(t.id, t.status, t.message) FROM Transaction t WHERE t.id =:pId")
    Optional<TransferResponseDto> obtenerTransaccion(@Param("pId") String  pId);
}
