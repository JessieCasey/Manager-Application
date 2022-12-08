package com.football.manager.entity.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlayerUpdateDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("nickname")
    private String nickName;
    @JsonProperty("months_experience")
    private int monthsExperience = -1;
}
