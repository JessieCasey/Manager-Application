package com.football.manager.entity.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The PlayerCreateDTO class is required if we want to create the player entity.
 */

@Getter
@Setter
public class PlayerCreateDTO {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("months_experience")
    private int monthsExperience;
    private LocalDate birthday;

    @JsonProperty("current_team_name")
    private String currentTeamName;

    private String position;
}
