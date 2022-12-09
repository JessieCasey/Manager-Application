package com.football.manager.entity.bill.dao;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.bill.BillDeletedDTO;

import java.util.List;
import java.util.UUID;

/**
 * The BillDAO interface {@link Bill}
 */

public interface BillDAO {
    List<Bill> getBills();

    Bill saveBill(Bill team);

    Bill getBill(UUID id);

    BillDeletedDTO deleteBill(UUID id);
}
