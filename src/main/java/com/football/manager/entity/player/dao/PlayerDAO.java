package com.football.manager.entity.player.dao;

import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.dto.PlayerDeletedDTO;

import java.util.List;

public interface PlayerDAO {
    List<Player> getPlayers();

    Player savePlayer(Player team);

    Player getPlayer(int id);

    PlayerDeletedDTO deletePlayer(int id);
}
