package com.football.manager.entity.transaction.service;

import com.football.manager.entity.transaction.Transaction;
import com.football.manager.entity.transaction.dao.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDAO transactionDAO;

    @Autowired
    public TransactionServiceImpl(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionDAO.getTransactions();
    }

    @Override
    @Transactional
    public Transaction saveTransaction(Transaction trans) {
        return transactionDAO.saveTransaction(trans);
    }


    @Override
    public Transaction getTransaction(UUID id) {
        return transactionDAO.getTransaction(id);
    }

}
