package com.football.manager.entity.manager;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.bill.BillRepresentDTO;
import com.football.manager.entity.player.Player;
import com.football.manager.entity.transaction.Transaction;
import com.football.manager.entity.transaction.TransactionRepresentDTO;

import java.util.UUID;

/**
 * The ManagerService interface operate with {@link Bill} {@link Transaction}
 */

public interface ManagerService {

    BillRepresentDTO makeAndSendBill(int playerId, int transferToTeamId);

    TransactionRepresentDTO payBill(UUID bill);

    TeamHistoryDTO getHistory(int teamId);

}
