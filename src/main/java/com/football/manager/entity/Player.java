package com.football.manager.entity;

import com.football.manager.entity.team.Team;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author Artem
 */

@Getter
@Setter
@Entity
@Table(name="PLAYER")
public class Player implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "nickname")
    private String nickName;

    private int experience;
    private LocalDate birthday;

    @Column(name = "arrivaldate")
    private LocalDate arrivalDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Position> positionsPlayed;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Team> clubsPlayed;

    @OneToOne(fetch = FetchType.EAGER)
    private Team motherClub;
}
