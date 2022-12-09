package com.football.manager.entity.bill.dao;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.bill.BillDeletedDTO;
import com.football.manager.exception.cover.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The BillDAOImpl class implements BillDAO interface to create methods {@link BillDAO}
 */

@Repository
public class BillDAOImpl implements BillDAO {

    private final HibernateTemplate hibernateTemplate;

    @Autowired
    public BillDAOImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public List<Bill> getBills() {
        List<Bill> bills = hibernateTemplate.loadAll(Bill.class);
        if (bills.size() == 0)
            return new ArrayList<>();
        else
            return hibernateTemplate.loadAll(Bill.class);
    }

    @Override
    public Bill saveBill(Bill bill) {
        hibernateTemplate.persist(bill);
        return bill;
    }

    @Override
    public Bill getBill(UUID id) {
        Bill bill = hibernateTemplate.get(Bill.class, id);
        if (bill != null)
            return bill;
        else
            throw new EntityNotFoundException("Bill with id '" + id + "'" + " is not found");
    }

    @Override
    public BillDeletedDTO deleteBill(UUID id) {
        Bill billToBeDeleted = hibernateTemplate.get(Bill.class, id);
        if (null != billToBeDeleted) {
            BillDeletedDTO dto = BillDeletedDTO.from(billToBeDeleted);
            hibernateTemplate.delete(billToBeDeleted);
            return dto;
        } else
            throw new EntityNotFoundException("Bill with id '" + id + "'" + " is not found");
    }
}
