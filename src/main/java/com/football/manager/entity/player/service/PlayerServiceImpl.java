package com.football.manager.entity.player.service;

import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.dao.PlayerDAO;
import com.football.manager.entity.player.dto.PlayerDeletedDTO;
import com.football.manager.entity.player.dto.PlayerUpdateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDAO playerDAO;

    @Autowired
    public PlayerServiceImpl(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public List<Player> getPlayers() {
        return playerDAO.getPlayers();
    }

    @Override
    @Transactional
    public Player savePlayer(Player player) {
        player.setArrivalDate(LocalDate.now());
        if (PlayerService.calculateAge(player.getBirthday(), LocalDate.now()) >= 16)
            return playerDAO.savePlayer(player);
        else
            throw new IllegalArgumentException("Federation doesn't accept persons who are younger than 16 years old");
    }

    @Override
    public Player getPlayer(int id) {
        return playerDAO.getPlayer(id);
    }

    @Override
    @Transactional
    public PlayerDeletedDTO deletePlayer(int id) {
        return playerDAO.deletePlayer(id);
    }

    @Override
    @Transactional
    public Player updatePlayer(PlayerUpdateDTO updateDTO) {
        Player team = playerDAO.getPlayer(updateDTO.getId());
        if (!(updateDTO.getMonthsExperience() < 0))
            team.setMonthsExperience(updateDTO.getMonthsExperience());
        if (updateDTO.getFirstName() != null)
            team.setFirstName(updateDTO.getFirstName());
        if (updateDTO.getLastName() != null)
            team.setLastName(updateDTO.getLastName());
        return team;
    }
}
