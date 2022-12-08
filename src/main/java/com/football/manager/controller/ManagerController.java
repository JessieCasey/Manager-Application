package com.football.manager.controller;

import com.football.manager.entity.bill.BillRepresentDTO;
import com.football.manager.entity.manager.ManagerService;
import com.football.manager.entity.manager.TeamHistoryDTO;
import com.football.manager.entity.transaction.TransactionRepresentDTO;
import com.football.manager.exception.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.UUID;

@RestController
@RequestMapping("api/manager")
@Slf4j
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

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
