package com.football.manager.entity.transaction.service;

import com.football.manager.entity.transaction.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<Transaction> getTransactions();

    Transaction saveTransaction(Transaction trans);

    Transaction getTransaction(UUID id);

    void deleteById(UUID id);
}
