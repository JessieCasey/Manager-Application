package com.football.manager.entity.transaction;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.player.dto.PlayerRepresentDTO;
import com.football.manager.entity.team.dto.TeamDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The TransactionRepresentDTO class is required if we want to represent the transaction entity in flexible format.
 */

@Getter
@Setter
@Builder
public class TransactionRepresentDTO {

    private UUID id;

    private String commissionPercentage;
    private int commission;
    private int price;
    private int totalPrice;
    private PlayerRepresentDTO player;

    private TeamDTO fromTeam;
    private TeamDTO toTeam;

    private LocalDateTime date;

    public static TransactionRepresentDTO from(Bill bill) {
        return builder()
                .id(bill.getId())
                .commissionPercentage(bill.getCommission() + "%")
                .commission((int)(bill.getPrice()*(bill.getCommission()/100.0f)))
                .price(bill.getPrice())
                .totalPrice(bill.getTotalPrice())
                .player(PlayerRepresentDTO.from(bill.getPlayer()))
                .fromTeam(TeamDTO.from(bill.getFromTeam()))
                .toTeam(TeamDTO.from(bill.getToTeam()))
                .date(LocalDateTime.now())
                .build();
    }

    public static TransactionRepresentDTO from(Transaction transaction) {
        return builder()
                .id(transaction.getId())
                .commissionPercentage(transaction.getCommission() + "%")
                .commission((int)(transaction.getPrice()*(transaction.getCommission()/100.0f)))
                .totalPrice(transaction.getTotalPrice())
                .player(PlayerRepresentDTO.from(transaction.getPlayer()))
                .fromTeam(transaction.getFromTeam() != null ? TeamDTO.from(transaction.getFromTeam()) : null)
                .toTeam(TeamDTO.from(transaction.getToTeam()))
                .date(transaction.getDate())
                .build();
    }
}
