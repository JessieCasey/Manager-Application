package com.football.manager.entity.team;

import com.football.manager.entity.bill.Bill;
import com.football.manager.entity.player.Player;
import com.football.manager.entity.team.dto.TeamCreateDTO;
import com.football.manager.entity.transaction.Transaction;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TEAM")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_start15")
    @SequenceGenerator(name="team_start15", initialValue = 15)
    private int id;

    @Column(name = "club_name")
    private String clubName;

    private String founder;

    @Column(name = "founded_date", nullable = true)
    private LocalDate foundedDate;

    @Min(0)
    @Max(10)
    private int commission;

    @Column(nullable = true)
    private float budget;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TEAM_PLAYER",
            joinColumns = {@JoinColumn(name = "Team_id")},
            inverseJoinColumns = {@JoinColumn(name = "players_id")}
    )
    private List<Player> players = new ArrayList<>();

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Bill> bills = new ArrayList<>();

    public static Team from(TeamCreateDTO teamDTO) {
        return builder()
                .clubName(teamDTO.getClubName())
                .founder(teamDTO.getFounder())
                .commission(teamDTO.getCommission())
                .foundedDate(teamDTO.getFounded())
                .budget(teamDTO.getBudget())
                .build();
    }
}
