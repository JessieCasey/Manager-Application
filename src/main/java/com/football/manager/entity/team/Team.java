package com.football.manager.entity.team;

import com.football.manager.entity.Player;
import com.football.manager.entity.Transaction;
import com.football.manager.entity.team.dto.TeamCreateDTO;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_start5")
    @SequenceGenerator(name="team_start5", initialValue = 5)
    private int id;

    @Column(name = "club_name")
    private String clubName;

    private String founder;

    @Min(0)
    @Max(10)
    private int commission;

    @Column(name = "founded_date", nullable = true)
    private LocalDate foundedDate;

    @Column(nullable = true)
    private float budget;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Player> players;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Transaction> bills;

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
