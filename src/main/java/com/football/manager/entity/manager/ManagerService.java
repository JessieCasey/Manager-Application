package com.football.manager.entity.manager;

import com.football.manager.entity.bill.BillRepresentDTO;
import com.football.manager.entity.transaction.TransactionRepresentDTO;

import java.util.UUID;

public interface ManagerService {

    BillRepresentDTO makeAndSendBill(int playerId, int transferToTeamId);

    TransactionRepresentDTO payBill(UUID bill);

    TeamHistoryDTO getHistory(int teamId);

}
