package com.example.reward.dao;

import com.example.reward.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByDatetimeBetweenAndCustomerId(LocalDateTime start, LocalDateTime end, Long customerID);
}
