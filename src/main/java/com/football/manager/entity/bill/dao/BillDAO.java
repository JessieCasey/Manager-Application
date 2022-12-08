package com.football.manager.entity.bill.dao;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.bill.DeletedBillDTO;

import java.util.List;
import java.util.UUID;

public interface BillDAO {
    List<Bill> getBills();

    Bill saveBill(Bill team);

    Bill getBill(UUID id);

    DeletedBillDTO deleteBill(UUID id);
}
