package com.football.manager.entity.transaction;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.player.dto.PlayerRepresentDTO;
import com.football.manager.entity.team.dto.TeamDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TransactionRepresentDTO {

    private UUID id;

    private int paid;
    private float commission;
    private PlayerRepresentDTO player;

    private TeamDTO fromTeam;
    private TeamDTO toTeam;

    private LocalDateTime date;

    public static TransactionRepresentDTO from(Bill bill) {
        return builder()
                .id(bill.getId())
                .paid(bill.getTotalPrice())
                .commission(bill.getCommission())
                .player(PlayerRepresentDTO.from(bill.getPlayer()))
                .fromTeam(TeamDTO.from(bill.getFromTeam()))
                .toTeam(TeamDTO.from(bill.getToTeam()))
                .date(LocalDateTime.now())
                .build();
    }

    public static TransactionRepresentDTO from(Transaction transaction) {
        return builder()
                .id(transaction.getId())
                .paid(transaction.getPaid())
                .commission(transaction.getCommission())
                .player(PlayerRepresentDTO.from(transaction.getPlayer()))
                .fromTeam(TeamDTO.from(transaction.getFromTeam()))
                .toTeam(TeamDTO.from(transaction.getToTeam()))
                .date(transaction.getDate())
                .build();
    }
}
