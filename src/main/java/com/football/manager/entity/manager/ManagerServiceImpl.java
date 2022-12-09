package com.football.manager.entity.manager;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.bill.BillRepresentDTO;
import com.football.manager.entity.bill.service.BillService;
import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.service.PlayerService;
import com.football.manager.entity.team.Team;
import com.football.manager.entity.team.service.TeamService;
import com.football.manager.entity.transaction.Transaction;
import com.football.manager.entity.transaction.TransactionRepresentDTO;
import com.football.manager.entity.transaction.service.TransactionService;
import com.football.manager.exception.EntityNotFoundException;
import com.football.manager.exception.PaymentDeclinedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final PlayerService playerService;
    private final TeamService teamService;
    private final BillService billService;
    private final TransactionService transactionService;

    @Autowired
    public ManagerServiceImpl(PlayerService playerService, TeamService teamService, BillService billService, TransactionService transactionService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.billService = billService;
        this.transactionService = transactionService;
    }

    @Override
    @Transactional
    public BillRepresentDTO makeAndSendBill(int playerId, int transferToTeamId) {
        Player player = playerService.getPlayer(playerId);

        if (player == null)
            throw new EntityNotFoundException("Player is not found with id '" + playerId + "'");

        int currentTeamId = PlayerService.getCurrentTeamId(player);

        Team fromTeam = null;
        Team toTeam = teamService.getTeam(transferToTeamId);
        if (currentTeamId != -1) {
            fromTeam = teamService.getTeam(currentTeamId);
            if (fromTeam.getClubName().equals(toTeam.getClubName()))
                throw new IllegalArgumentException("Player is already in the team '" + toTeam.getClubName() + "'");
        }

        Bill billToSend = (fromTeam == null) ?
                Bill.from(player, toTeam) :
                Bill.from(player, fromTeam, toTeam);

        billToSend.setId(UUID.randomUUID());
        billService.saveBill(billToSend);

        sendBillTo(billToSend, toTeam);
        return BillRepresentDTO.from(billToSend);
    }

    @Transactional
    public void sendBillTo(Bill billToSend, Team toTeam) {
        toTeam.getBills().add(billToSend);
    }

    @Override
    @Transactional
    public TransactionRepresentDTO payBill(UUID billId) {
        Bill bill = billService.getBill(billId);
        Player player = bill.getPlayer();
        Team fromTeam = bill.getFromTeam();
        Team toTeam = bill.getToTeam();

        if (toTeam.getBudget() >= bill.getTotalPrice()) {
            toTeam.setBudget(toTeam.getBudget() - bill.getTotalPrice());
            toTeam.getPlayers().add(player);

            if (fromTeam != null) {
                fromTeam.getPlayers().remove(player);
                fromTeam.setBudget(fromTeam.getBudget() + bill.getTotalPrice());
            }

            toTeam.getBills().remove(bill);
            Transaction transaction = transactionService.saveTransaction(Transaction.from(bill));
            toTeam.getTransactions().add(transaction);

            player.getClubsPlayed().add(toTeam);
            billService.deleteBill(billId);
            return TransactionRepresentDTO.from(transaction);
        } else
            throw new PaymentDeclinedException("Payment is declined: there are not enough funds in the account");
    }

    @Override
    public TeamHistoryDTO getHistory(int teamId) {
        return TeamHistoryDTO.from(teamService.getTeam(teamId));
    }
}
