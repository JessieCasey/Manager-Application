package com.football.manager.controller;

import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.dto.*;
import com.football.manager.entity.player.service.PlayerService;
import com.football.manager.entity.team.Team;
import com.football.manager.entity.team.service.TeamService;
import com.football.manager.exception.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/player")
@Slf4j
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<?> fetchPlayers(WebRequest request) {
        log.info("[GET] Request to 'fetchPlayers'");
        try {
            List<Player> players = playerService.getPlayers();

            Object content;
            if (players.size() != 0) {
                content = players.stream().map(PlayerRepresentDTO::from);
            } else {
                content = new MessageResponse(200, "There are no players in database", request);
            }
            return ResponseEntity.ok().body(content);

        } catch (Exception e) {
            log.error("Error in method 'fetchPlayers': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<?> fetchPLayerById(@PathVariable int playerId, WebRequest request) {
        log.info("[GET] Request to 'fetchPLayerById'");
        try {
            Player playerById = playerService.getPlayer(playerId);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(playerById.getId())
                    .toUri();

            log.info("[GET] Request to 'fetchPLayerById': Player is founded with id '" + playerById + "'");
            return ResponseEntity.created(location).body(PlayerDetailedDTO.from(playerById));
        } catch (Exception e) {
            log.error("Error in method 'fetchPLayerById': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody PlayerCreateDTO playerDTO, WebRequest request) {
        log.info("[POST] Request to 'createPlayer'");
        try {
            Team team = teamService.getByClubName(playerDTO.getCurrentTeamName());
            Player player = playerService.savePlayer(Player.from(playerDTO, team));

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(player.getId())
                    .toUri();

            log.info("[POST] Request to 'createPlayer': Player is created");
            return ResponseEntity.created(location).body(PlayerRepresentDTO.from(player));
        } catch (Exception e) {
            log.error("Error in method 'createPlayer': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    @PutMapping("/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updatePlayer(@PathVariable int teamId,
                                        @RequestBody PlayerUpdateDTO updateDTO,
                                        WebRequest request) {
        log.info("[PUT] Request to 'updatePlayer'");
        try {
            updateDTO.setId(teamId);
            Player player = playerService.updatePlayer(updateDTO);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(player.getId())
                    .toUri();

            log.info("[PUT] Request to 'updatePlayer': PLayer is updated");
            return ResponseEntity.created(location).body(PlayerRepresentDTO.from(player));
        } catch (Exception e) {
            log.error("Error in method 'updatePlayer': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable int playerId, WebRequest request) {
        log.info("[DELETE] Request to 'deletePlayer'");
        try {
            PlayerDeletedDTO dto = playerService.deletePlayer(playerId);

            log.info("[DELETE] Request to 'deletePlayer': Player is deleted");
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            log.error("Error in method 'deletePlayer': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }
}
