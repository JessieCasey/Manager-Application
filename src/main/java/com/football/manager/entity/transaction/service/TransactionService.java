package com.football.manager.entity.transaction.service;

import com.football.manager.entity.team.service.TeamServiceImpl;
import com.football.manager.entity.transaction.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * The TransactionService interface is required to create TransactionServiceImpl {@link TransactionServiceImpl}
 */

public interface TransactionService {
    List<Transaction> getTransactions();

    Transaction saveTransaction(Transaction trans);

    Transaction getTransaction(UUID id);

    void deleteById(UUID id);
}
