package com.football.manager.entity;

import com.football.manager.entity.team.Team;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float paid;

    private float commission;

    @OneToOne(fetch = FetchType.EAGER)
    private Player player;
    @OneToOne(fetch = FetchType.EAGER)
    private Team from;
    @OneToOne(fetch = FetchType.EAGER)
    private Team to;

    private LocalDateTime date;
}
