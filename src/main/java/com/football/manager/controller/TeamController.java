package com.football.manager.controller;

import com.football.manager.entity.team.Team;
import com.football.manager.entity.team.dto.TeamCreateDTO;
import com.football.manager.entity.team.dto.TeamDeletedDTO;
import com.football.manager.entity.team.dto.TeamRepresentDTO;
import com.football.manager.entity.team.dto.TeamUpdateDTO;
import com.football.manager.entity.team.service.TeamService;
import com.football.manager.exception.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * The TeamController is required to operate with basic CRUD operations {@link Team}
 */

@RestController
@RequestMapping("api/team")
@Slf4j
public class TeamController {

    private final TeamService teamService;

    /**
     * Constructor for {@link TeamController}.
     * @param teamService  {@link TeamService}
     */
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Method that fetching teams
     * @return List<TeamRepresentDTO> object in case of success. {@link List<TeamRepresentDTO>}
     */
    @GetMapping
    public ResponseEntity<?> fetchTeams(WebRequest request) {
        log.info("[GET] Request to 'fetchTeams'");
        try {
            List<Team> teams = teamService.getTeams();

            log.info("[GET] Request to 'fetchTeams': Team is created");
            if (teams.size() != 0) {
                return ResponseEntity.ok().body(teams.stream().map(TeamRepresentDTO::from));
            } else {
                return ResponseEntity.ok().body(new MessageResponse(200, "There are no teams in database", request));
            }
        } catch (Exception e) {
            log.error("Error in method 'fetchTeams': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    /**
     * Method that fetching team by ID
     * @return TeamRepresentDTO object in case of success. {@link TeamRepresentDTO}
     */
    @GetMapping("/{teamId}")
    public ResponseEntity<?> fetchTeamById(@PathVariable int teamId, WebRequest request) {
        log.info("[GET] Request to 'fetchTeamById'");
        try {
            Team teamById = teamService.getTeam(teamId);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(teamById.getId())
                    .toUri();

            log.info("[GET] Request to 'fetchTeamById': Team is founded with id '" + teamId + "'");
            return ResponseEntity.created(location).body(TeamRepresentDTO.from(teamById));
        } catch (Exception e) {
            log.error("Error in method 'fetchTeamById': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    /**
     * Method that creating team by TeamCreateDTO {@link TeamCreateDTO}
     * @return TeamRepresentDTO object in case of success
     */
    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody TeamCreateDTO teamDTO, WebRequest request) {
        log.info("[POST] Request to 'createTeam'");
        try {
            Team team = teamService.saveTeam(Team.from(teamDTO));

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(team.getId())
                    .toUri();

            log.info("[POST] Request to 'createTeam': Team is created");
            return ResponseEntity.created(location).body(TeamRepresentDTO.from(team));
        } catch (Exception e) {
            log.error("Error in method 'createTeam': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    /**
     * Method that updating team by PlayerCreateDTO {@link TeamUpdateDTO}
     * @param teamId id of the team to be updated
     * @param updateDTO DTO to transfer data {@link TeamUpdateDTO}
     * @return TeamRepresentDTO object in case of success
     */
    @PutMapping("/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateTeam(@PathVariable int teamId, @RequestBody TeamUpdateDTO updateDTO,
                                        WebRequest request) {
        log.info("[PUT] Request to 'updateTeam'");
        try {
            updateDTO.setId(teamId);
            Team team = teamService.updateTeam(updateDTO);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(team.getId())
                    .toUri();

            log.info("[PUT] Request to 'updateTeam': Team is updated");
            return ResponseEntity.created(location).body(TeamRepresentDTO.from(team));
        } catch (Exception e) {
            log.error("Error in method 'updateTeam': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    /**
     * Method that deleting team
     * @param teamId id of the team to be updated
     * @return TeamDeletedDTO object in case of success
     */
    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable int teamId, WebRequest request) {
        log.info("[DELETE] Request to 'deleteTeam'");
        try {
            TeamDeletedDTO dto = teamService.deleteTeam(teamId);

            log.info("[DELETE] Request to 'deleteTeam': Team is deleted");
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            log.error("Error in method 'deleteTeam': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }
}
