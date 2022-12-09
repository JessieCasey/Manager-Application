package com.football.manager.entity.team.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.football.manager.entity.player.dto.PlayerRepresentDTO;
import com.football.manager.entity.team.Team;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TeamRepresentDTO class is required if we want to represent the team entity in flexible format.
 */

@SuperBuilder
@Getter
@Setter
public class TeamRepresentDTO extends TeamDTO {

    private float commission;
    private int budget;
    private List<PlayerRepresentDTO> players;
    @JsonProperty("count_of_transactions")
    private int transactions;

    public static TeamRepresentDTO from(Team team) {
        return builder()
                .id(team.getId())
                .clubName(team.getClubName())
                .founder(team.getFounder())
                .commission(team.getCommission())
                .foundedDate(team.getFoundedDate())
                .budget(team.getBudget())
                .players(team.getPlayers() != null ? team.getPlayers().stream().map(PlayerRepresentDTO::from).collect(Collectors.toList()) : new ArrayList<>())
                .transactions(team.getTransactions() != null ? team.getTransactions().size() : 0)
                .build();
    }
}
