package com.football.manager.controller;

import com.football.manager.entity.team.Team;
import com.football.manager.entity.team.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/manager")
@Slf4j
public class ManagerController {

    private final TeamService teamService;

    public ManagerController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public Team fetchAdvertsWithPagination(
            @PathVariable int id) {
        return teamService.getTeam(id);
    }

}
