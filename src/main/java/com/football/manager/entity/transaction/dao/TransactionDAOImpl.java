package com.football.manager.entity.transaction.dao;

import com.football.manager.entity.transaction.Transaction;
import com.football.manager.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The TransactionDAOImpl class implements TransactionDAO interface to create methods {@link TransactionDAO}
 */

@Repository
public class TransactionDAOImpl implements TransactionDAO {

    private final HibernateTemplate hibernateTemplate;

    @Autowired
    public TransactionDAOImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public List<Transaction> getTransactions() {
        List<Transaction> trans = hibernateTemplate.loadAll(Transaction.class);
        if (trans.size() == 0)
            return new ArrayList<>();
        else
            return hibernateTemplate.loadAll(Transaction.class);
    }

    @Override
    public Transaction saveTransaction(Transaction trans) {
        hibernateTemplate.persist(trans);

        return trans;
    }

    @Override
    public Transaction getTransaction(UUID id) {
        Transaction trans = hibernateTemplate.get(Transaction.class, id);
        if (trans != null)
            return trans;
        else
            throw new EntityNotFoundException("Transaction with id '" + id + "'" + " is not found");
    }

    @Override
    public void deleteById(UUID id) {
        Transaction transaction = hibernateTemplate.get(Transaction.class, id);
        if (null != transaction) {
            hibernateTemplate.delete(transaction);
        } else
            throw new EntityNotFoundException("Transaction with id '" + id + "'" + " is not found");
    }
}
