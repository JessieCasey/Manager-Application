package com.football.manager.entity.team.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.football.manager.entity.team.Team;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class TeamDeletedDTO {
    private int id;
    @JsonProperty("club_name")
    private String clubName;
    private String founder;
    @JsonProperty("founded_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate foundedDate;
    private float budget;
    @JsonProperty("count_of_players")
    private int countOfPlayers;

    public static TeamDeletedDTO from(Team team) {
        return builder()
                .id(team.getId())
                .clubName(team.getClubName())
                .founder(team.getFounder())
                .foundedDate(team.getFoundedDate())
                .budget(team.getBudget())
                .countOfPlayers(team.getPlayers().size())
                .build();
    }
}
