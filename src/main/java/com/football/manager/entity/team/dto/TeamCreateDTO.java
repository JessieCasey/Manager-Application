package com.football.manager.entity.team.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The TeamCreateDTO class is required if we want to create the team.
 */

@Getter
@Setter
public class TeamCreateDTO {

    @JsonProperty("club_name")
    private String clubName;
    private String founder;
    private LocalDate founded;
    private int budget;
    private int commission;

}
