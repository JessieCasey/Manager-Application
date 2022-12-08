package com.football.manager.entity.team.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.football.manager.entity.Transaction;
import com.football.manager.entity.player.dto.PlayerRepresentDTO;
import com.football.manager.entity.team.Team;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class TeamRepresentDTO {

    private int id;
    @JsonProperty("club_name")
    private String clubName;
    private String founder;
    private int commission;
    @JsonProperty("founded_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate foundedDate;
    private float budget;
    private List<PlayerRepresentDTO> players;
    private List<Transaction> bills;

    public static TeamRepresentDTO from(Team team) {
        return builder()
                .id(team.getId())
                .clubName(team.getClubName())
                .founder(team.getFounder())
                .commission(team.getCommission())
                .foundedDate(team.getFoundedDate())
                .budget(team.getBudget())
                .players(team.getPlayers() == null ? new ArrayList<>() : team.getPlayers().stream().map(PlayerRepresentDTO::from).collect(Collectors.toList()))
                .bills(team.getBills())
                .build();
    }
}
