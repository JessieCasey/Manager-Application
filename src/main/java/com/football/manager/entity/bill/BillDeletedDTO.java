package com.football.manager.entity.bill;

import com.football.manager.entity.player.Player;
import com.football.manager.entity.team.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class BillDeletedDTO {

    private UUID id;
    private int price;
    private float commission;
    private int totalPrice;

    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;

    private Player player;
    private Team fromTeam;
    private Team toTeam;

    private boolean isPaid = false;

    private LocalDateTime deletedAt;

    public static BillDeletedDTO from(Bill bill) {
        return builder()
                .id(UUID.randomUUID())
                .price(bill.getPrice())
                .commission(bill.getCommission())
                .totalPrice(bill.getTotalPrice())
                .createdAt(bill.getCreatedAt())
                .expiredAt(bill.getExpiredAt())
                .player(bill.getPlayer())
                .fromTeam(bill.getFromTeam())
                .toTeam(bill.getToTeam())
                .deletedAt(LocalDateTime.now())
                .build();
    }
}
