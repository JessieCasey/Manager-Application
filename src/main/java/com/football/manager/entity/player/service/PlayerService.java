package com.football.manager.entity.player.service;

import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.dto.PlayerDeletedDTO;
import com.football.manager.entity.player.dto.PlayerUpdateDTO;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * The PlayerService interface is required to create PlayerServiceImpl {@link PlayerServiceImpl}
 */

public interface PlayerService {
    List<Player> getPlayers();

    Player savePlayer(Player team);

    Player getPlayer(int id);

    PlayerDeletedDTO deletePlayer(int id);

    Player updatePlayer(PlayerUpdateDTO updateDTO);

    static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    static int getCurrentTeamId(Player player) {
        return player.getClubsPlayed()
                .get(player.getClubsPlayed().size() != 0 ?
                        player.getClubsPlayed().size() - 1 : 0).getId();
    }
}
