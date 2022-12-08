package com.football.manager.entity.team.service;

import com.football.manager.entity.team.Team;
import com.football.manager.entity.team.dao.TeamDAO;
import com.football.manager.entity.team.dto.TeamDeletedDTO;
import com.football.manager.entity.team.dto.TeamUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamDAO teamDAO;

    @Autowired
    public TeamServiceImpl(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public List<Team> getTeams() {
        return teamDAO.getTeams();
    }

    @Override
    @Transactional
    public Team saveTeam(Team team) {
        if (team.getCommission() < 0 || team.getCommission() > 10)
            throw new IllegalArgumentException("The commission should have been in range from 0 to 10");
        else
            return teamDAO.saveTeam(team);
    }

    @Override
    @Transactional
    public Team updateTeam(TeamUpdateDTO updateDTO) {
        Team team = teamDAO.getTeam(updateDTO.getId());
        if (!(updateDTO.getCommission() < 0))
            team.setCommission(updateDTO.getCommission());
        if (!(updateDTO.getBudget() < 0))
            team.setBudget(updateDTO.getBudget());
        if (updateDTO.getClubName() != null)
            team.setClubName(updateDTO.getClubName());
        return team;
    }

    @Override
    public Team getTeam(int id) {
        return teamDAO.getTeam(id);
    }

    @Override
    @Transactional
    public TeamDeletedDTO deleteTeam(int id) {
        return teamDAO.deleteTeam(id);
    }
}
