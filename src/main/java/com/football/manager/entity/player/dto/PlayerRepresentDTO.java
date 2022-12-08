package com.football.manager.entity.player.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.football.manager.entity.Player;
import com.football.manager.entity.Position;
import com.football.manager.entity.team.Team;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class PlayerRepresentDTO {
    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("nickname")
    private String nickName;
    private int experience;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyyy")
    private LocalDate birthday;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyyy")
    @JsonProperty("arrival_date")
    private LocalDate arrivalDate;
    @JsonProperty("positions")
    private List<Position> positionsPlayed;
    @JsonProperty("clubs_played")
    private List<Team> clubsPlayed;
    @JsonProperty("mother_club")
    private Team motherClub;

    public static PlayerRepresentDTO from(Player player) {
        return builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .nickName(player.getNickName())
                .experience(player.getExperience())
                .birthday(player.getBirthday())
                .positionsPlayed(player.getPositionsPlayed())
                .clubsPlayed(player.getClubsPlayed())
                .motherClub(player.getMotherClub())
                .build();
    }
}
