package com.football.manager.entity.team.dao;

import com.football.manager.entity.team.Team;
import com.football.manager.entity.team.dto.TeamDeletedDTO;

import java.util.List;

public interface TeamDAO {
    List<Team> getTeams();

    Team saveTeam(Team team);

    Team getTeam(int id);

    TeamDeletedDTO deleteTeam(int id);
}
