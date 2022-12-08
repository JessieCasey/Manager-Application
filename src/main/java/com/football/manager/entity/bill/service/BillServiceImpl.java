package com.football.manager.entity.bill.service;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.bill.DeletedBillDTO;
import com.football.manager.entity.bill.dao.BillDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BillServiceImpl implements BillService {

    private final BillDAO billDAO;

    @Autowired
    public BillServiceImpl(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    @Override
    public List<Bill> getBills() {
        return billDAO.getBills();
    }

    @Override
    @Transactional
    public Bill saveBill(Bill bill) {
        return billDAO.saveBill(bill);
    }

    @Override
    public Bill getBill(UUID id) {
        return billDAO.getBill(id);
    }

    @Override
    @Transactional
    public DeletedBillDTO deleteBill(UUID id) {
        return billDAO.deleteBill(id);
    }
}
