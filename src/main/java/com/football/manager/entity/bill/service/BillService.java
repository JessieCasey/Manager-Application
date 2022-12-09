package com.football.manager.entity.bill.service;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.bill.BillDeletedDTO;

import java.util.List;
import java.util.UUID;

/**
 * The BillService interface {@link Bill}
 */

public interface BillService {
    List<Bill> getBills();

    Bill saveBill(Bill team);

    Bill getBill(UUID id);

    BillDeletedDTO deleteBill(UUID id);
}
