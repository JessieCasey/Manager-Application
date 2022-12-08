package com.football.manager.entity.team.service;

import com.football.manager.entity.team.Team;
import com.football.manager.entity.team.dto.TeamDeletedDTO;
import com.football.manager.entity.team.dto.TeamUpdateDTO;

import java.util.List;

public interface TeamService {
    List<Team> getTeams();

    Team saveTeam(Team team);

    Team getTeam(int id);

    Team getByClubName(String title);

    TeamDeletedDTO deleteTeam(int id);

    Team updateTeam(TeamUpdateDTO updateDTO);

}
