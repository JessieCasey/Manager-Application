package com.football.manager.entity.bill;

import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.service.PlayerService;
import com.football.manager.entity.team.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "BILL")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    private UUID id;

    private int price;

    private int commission;

    private float totalPrice;

    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;

    @OneToOne(fetch = FetchType.EAGER)
    private Player player;
    @OneToOne(fetch = FetchType.EAGER)
    private Team fromTeam;
    @OneToOne(fetch = FetchType.EAGER)
    private Team toTeam;

    public static Bill from(Player player, Team from, Team to) {
        int priceOfPlayer = getPriceOfPlayer(player);
        return builder()
                .id(UUID.randomUUID())
                .price(priceOfPlayer)
                .commission(from.getCommission())
                .totalPrice(priceOfPlayer + (from.getCommission() * priceOfPlayer))
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMonths(6))
                .player(player)
                .fromTeam(from)
                .toTeam(to)
                .build();
    }

    public static Bill from(Player player, Team to) {
        int priceOfPlayer = getPriceOfPlayer(player);
        return builder()
                .id(UUID.randomUUID())
                .price(priceOfPlayer)
                .commission(0)
                .totalPrice(priceOfPlayer)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMonths(6))
                .player(player)
                .toTeam(to)
                .build();
    }

    private static int getPriceOfPlayer(Player player) {
        return player.getMonthsExperience() * 100000 / PlayerService.calculateAge(player.getBirthday(), LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        return Objects.equals(id, bill.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
