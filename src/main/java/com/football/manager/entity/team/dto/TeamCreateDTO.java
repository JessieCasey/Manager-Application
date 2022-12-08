package com.football.manager.entity.team.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TeamCreateDTO {

    @JsonProperty("club_name")
    private String clubName;
    private String founder;
    private LocalDate founded;
    private float budget;
    private int commission;

}
