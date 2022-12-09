package com.football.manager.controller;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.bill.BillRepresentDTO;
import com.football.manager.entity.manager.ManagerService;
import com.football.manager.entity.manager.TeamHistoryDTO;
import com.football.manager.entity.transaction.Transaction;
import com.football.manager.entity.transaction.TransactionRepresentDTO;
import com.football.manager.exception.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.UUID;

/**
 * The ManagerController is required to transfer players between teams.
 * To transfer player it needs to pay the bill {@link Bill}
 * result of the payment is transaction (receipt) {@link Transaction}
 */

@RestController
@RequestMapping("api/manager")
@Slf4j
public class ManagerController {

    private final ManagerService managerService;

    /**
     * Constructor for {@link ManagerController}.
     * @param managerService  {@link ManagerService}
     */
    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    /**
     * Method that creating and return the bill to the team
     * (Every team has its own 'wallet', which can be used to make payment)
     *
     * @param playerId ID of the player to be transferred
     * @param transferToTeamId ID of the team to be player moved
     *
     * @return BillRepresentDTO object in case of success and send it automatically to the team. {@link BillRepresentDTO}
     */
    @GetMapping
    public ResponseEntity<?> getBill(@RequestParam int playerId, @RequestParam int transferToTeamId, WebRequest request) {
        log.info("[GET] Request to 'getBill'");
        try {
            BillRepresentDTO billToBePaid = managerService.makeAndSendBill(playerId, transferToTeamId);
            log.info("[GET] Request to 'getBill': Player is invited to the team");
            return ResponseEntity.status(HttpStatus.OK).body(billToBePaid);
        } catch (Exception e) {
            log.error("Error in method 'getBill': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    /**
     * Method that making payment and return the transaction (receipt) to the team
     * (Every necessary information is included in the bill to make payment automatically)
     *
     * @param billId ID of the bill to be paid by the team {@link UUID}
     *
     * @return TransactionRepresentDTO object in case of success and send it automatically to the team. {@link TransactionRepresentDTO}
     */
    @PostMapping
    public ResponseEntity<?> payBill(@RequestParam UUID billId, WebRequest request) {
        log.info("[POST] Request to 'payBill'");
        try {
            TransactionRepresentDTO confirmationAboutPayment = managerService.payBill(billId);
            return ResponseEntity.status(HttpStatus.OK).body(confirmationAboutPayment);
        } catch (Exception e) {
            log.error("Error in method 'payBill': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    /**
     * Method that fetching history of payments
     * (To discover the latest payment details)
     *
     * @param teamId ID of the team
     *
     * @return TeamHistoryDTO object in case of success. {@link TeamHistoryDTO}
     */
    @GetMapping("/history/{teamId}")
    public ResponseEntity<?> getHistory(@PathVariable int teamId, WebRequest request) {
        log.info("[POST] Request to 'getHistory'");
        try {
            TeamHistoryDTO dto = managerService.getHistory(teamId);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            log.error("Error in method 'getHistory': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }
}
