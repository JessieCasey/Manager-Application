package com.football.manager.entity.transaction;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.player.Player;
import com.football.manager.entity.team.Team;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TRANSACTION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction implements Serializable {

    @Id
    private UUID id;

    private float paid;

    private float commission;

    @OneToOne(fetch = FetchType.EAGER)
    private Player player;
    @OneToOne(fetch = FetchType.EAGER)
    private Team fromTeam;
    @OneToOne(fetch = FetchType.EAGER)
    private Team toTeam;

    private LocalDateTime date;

    public static Transaction from(Bill bill) {
        return builder()
                .id(bill.getId())
                .paid(bill.getTotalPrice())
                .commission(bill.getCommission())
                .player(bill.getPlayer())
                .fromTeam(bill.getFromTeam())
                .toTeam(bill.getToTeam())
                .date(LocalDateTime.now())
                .build();
    }
}
