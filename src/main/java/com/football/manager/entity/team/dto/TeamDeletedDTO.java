package com.football.manager.entity.team.dto;

import com.football.manager.entity.team.Team;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class TeamDeletedDTO extends TeamDTO {

    private int budget;

    public static TeamDeletedDTO from(Team team) {
        return builder()
                .id(team.getId())
                .clubName(team.getClubName())
                .founder(team.getFounder())
                .foundedDate(team.getFoundedDate())
                .budget(team.getBudget())
                .playersCount(team.getPlayers().size())
                .build();
    }
}
