package com.football.manager.entity.team.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * The TeamUpdateDTO class is required to update team
 */
@Getter
@Setter
public class TeamUpdateDTO {

    private int id;
    @JsonProperty("club_name")
    private String clubName;
    private int budget = -1;
    private float commission = -1;

}
