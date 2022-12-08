package com.football.manager.entity.team.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeamUpdateDTO {

    private int id;
    @JsonProperty("club_name")
    private String clubName;
    private float budget = -1;
    private int commission = -1;

}
