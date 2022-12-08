package com.football.manager.entity.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.service.PlayerService;
import com.football.manager.entity.team.dto.TeamDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
@Getter
@Setter
public class PlayerDetailedDTO extends PlayerDTO {

    @JsonProperty("clubs_played")
    private List<TeamDTO> clubsPlayed;

    @JsonProperty("mother_club")
    private TeamDTO motherClub;

    public static PlayerDetailedDTO from(Player player) {
        return  builder()
                .clubsPlayed(player.getClubsPlayed() != null ? player.getClubsPlayed().stream().map(TeamDTO::from).collect(Collectors.toList()) : new ArrayList<>())
                .motherClub(player.getMotherClub() != null ? TeamDTO.from(player.getMotherClub()) : null)
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
