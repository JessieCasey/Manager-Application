package com.football.manager.entity.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.service.PlayerService;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * The PlayerRepresentDTO class is required if we want to represent the player entity in flexible format.
 */
@SuperBuilder
@Getter
@Setter
public class PlayerRepresentDTO extends PlayerDTO {

    @JsonProperty("clubs_played_count")
    private int clubsPlayed;

    @JsonProperty("mother_club_name")
    private String motherClub;

    public static PlayerRepresentDTO from(Player player) {
        return builder()
                .clubsPlayed(player.getClubsPlayed() == null ? 0 : player.getClubsPlayed().size())
                .motherClub(player.getMotherClub() == null ? null : player.getMotherClub().getClubName())
                .id(player.getId())
                .arrivalDate(player.getArrivalDate())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .monthsExperience(player.getMonthsExperience())
                .age(PlayerService.calculateAge(player.getBirthday(), LocalDate.now()))
                .birthday(player.getBirthday())
                .position(player.getPosition().name())
                .build();
    }
}
