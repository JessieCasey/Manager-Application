package com.football.manager.entity.team.service;

import com.football.manager.entity.team.Team;
import com.football.manager.entity.team.dto.TeamDeletedDTO;
import com.football.manager.entity.team.dto.TeamUpdateDTO;

import java.util.List;

public interface TeamService {
    public List<Team> getTeams();

    public Team saveTeam(Team team);

    public Team getTeam(int id);

    public TeamDeletedDTO deleteTeam(int id);

    Team updateTeam(TeamUpdateDTO updateDTO);

}
