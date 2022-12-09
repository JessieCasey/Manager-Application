package com.football.manager.entity.player;

import com.football.manager.entity.player.dto.PlayerCreateDTO;
import com.football.manager.entity.team.Team;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


/**
 * The Player entity
 */

@Getter
@Setter
@Entity
@Table(name = "PLAYER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_start15")
    @SequenceGenerator(name = "player_start15", initialValue = 15)
    private int id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    private String nationality;

    private int monthsExperience;
    private LocalDate birthday;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToMany(mappedBy = "players", fetch = FetchType.EAGER)
    private List<Team> clubsPlayed;

    @OneToOne(fetch = FetchType.EAGER)
    private Team motherClub;

    public static Player from(PlayerCreateDTO player, Team team) {
        return builder()
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .monthsExperience(player.getMonthsExperience())
                .birthday(player.getBirthday())
                .motherClub(team)
                .position(Position.valueOf(player.getPosition()))
                .build();
    }
}
