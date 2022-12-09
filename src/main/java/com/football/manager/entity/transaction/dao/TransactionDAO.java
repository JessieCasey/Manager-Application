package com.football.manager.entity.transaction.dao;

import com.football.manager.entity.transaction.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * The TransactionDAO interface {@link Transaction}
 */

public interface TransactionDAO {
    List<Transaction> getTransactions();

    Transaction saveTransaction(Transaction team);

    Transaction getTransaction(UUID id);

    void deleteById(UUID id);
}
