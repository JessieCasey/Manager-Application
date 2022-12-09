package com.football.manager.entity.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.football.manager.entity.player.Player;
import com.football.manager.entity.team.dto.TeamRepresentDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The PlayerDeletedDTO class is required if we want to delete the player.
 */

@SuperBuilder
@Getter
@Setter
public class PlayerDeletedDTO extends PlayerDTO {

    @JsonProperty("clubs_played")
    private List<TeamRepresentDTO> clubsPlayed;

    @JsonProperty("mother_club")
    private TeamRepresentDTO motherClub;

    public static PlayerDeletedDTO from(Player player) {
        return builder()
                .clubsPlayed(player.getClubsPlayed().stream().map(TeamRepresentDTO::from).collect(Collectors.toList()))
                .motherClub(player.getMotherClub() != null ? TeamRepresentDTO.from(player.getMotherClub()) : null)
                .arrivalDate(player.getArrivalDate())
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .monthsExperience(player.getMonthsExperience())
                .birthday(player.getBirthday())
                .position(player.getPosition().name())
                .build();
    }
}
