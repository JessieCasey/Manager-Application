package com.football.manager.entity.bill;

import com.football.manager.entity.player.dto.PlayerRepresentDTO;
import com.football.manager.entity.team.dto.TeamDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The BillRepresentDTO class is required if we want to represent the bill entity in flexible format.
 */

@Getter
@Setter
@Builder
public class BillRepresentDTO {

    private UUID id;
    private int price;
    private float commission;
    private int totalPrice;

    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;

    private PlayerRepresentDTO player;
    private TeamDTO fromTeam;
    private TeamDTO toTeam;

    public static BillRepresentDTO from(Bill bill) {
        return builder()
                .id(bill.getId())
                .price(bill.getPrice())
                .commission(bill.getCommission())
                .totalPrice(bill.getTotalPrice())
                .createdAt(bill.getCreatedAt())
                .expiredAt(bill.getExpiredAt())
                .player(PlayerRepresentDTO.from(bill.getPlayer()))
                .fromTeam(TeamDTO.from(bill.getFromTeam()))
                .toTeam(TeamDTO.from(bill.getToTeam()))
                .build();
    }
}
