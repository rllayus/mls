package com.laredo.repository;

import com.laredo.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRespository extends JpaRepository<Transaction, String> {
}
