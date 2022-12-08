package com.football.manager.entity.player.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.service.PlayerService;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
public class PlayerDTO {

    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("months_experience")
    private int monthsExperience;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    @JsonProperty("arrival_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate arrivalDate;
    @JsonProperty("age")
    private int age;
    @JsonProperty("position")
    private String position;

    public static PlayerDTO from(Player player) {
        return builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .arrivalDate(player.getArrivalDate())
                .monthsExperience(player.getMonthsExperience())
                .age(PlayerService.calculateAge(player.getBirthday(), LocalDate.now()))
                .position(player.getPosition().name())
                .build();
    }
}
