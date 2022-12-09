package com.football.manager.controller;

import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.dto.*;
import com.football.manager.entity.player.service.PlayerService;
import com.football.manager.entity.team.Team;
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
 * The PlayerController is required to operate with basic CRUD operations {@link Player}
 */

@RestController
@RequestMapping("api/player")
@Slf4j
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    /**
     * Constructor for {@link PlayerController}.
     *
     * @param playerService {@link PlayerService}
     * @param teamService   {@link TeamService}
     */
    @Autowired
    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    /**
     * Method that fetching players
     *
     * @return List<PlayerRepresentDTO> object in case of success. {@link List<PlayerRepresentDTO>}
     */
    @GetMapping
    public ResponseEntity<?> fetchPlayers(WebRequest request) {
        log.info("[GET] Request to 'fetchPlayers'");

        List<Player> players = playerService.getPlayers();
        if (players.size() != 0) {
            return ResponseEntity.ok().body(players.stream().map(PlayerRepresentDTO::from));
        } else {
            return ResponseEntity.ok().body(new MessageResponse(200, "There are no players in database", request));
        }
    }

    /**
     * Method that fetching player by ID
     *
     * @return PlayerDetailedDTO object in case of success. {@link PlayerDetailedDTO}
     * PlayerDetailedDTO contains more details than PlayerRepresentDTO
     */
    @GetMapping("/{playerId}")
    public ResponseEntity<?> fetchPLayerById(@PathVariable int playerId) {
        log.info("[GET] Request to 'fetchPLayerById'");

        Player playerById = playerService.getPlayer(playerId);

        log.info("[GET] Request to 'fetchPLayerById': Player is founded with id '" + playerById + "'");
        return ResponseEntity.ok().body(PlayerDetailedDTO.from(playerById));
    }

    /**
     * Method that creating player by PlayerCreateDTO {@link PlayerCreateDTO}
     *
     * @return PlayerRepresentDTO object in case of success
     */
    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody PlayerCreateDTO playerDTO) {
        log.info("[POST] Request to 'createPlayer'");

        Team team = teamService.getByClubName(playerDTO.getCurrentTeamName());
        Player player = playerService.savePlayer(Player.from(playerDTO, team));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(player.getId())
                .toUri();

        log.info("[POST] Request to 'createPlayer': Player is created");
        return ResponseEntity.created(location).body(PlayerRepresentDTO.from(player));
    }

    /**
     * Method that updating player by PlayerCreateDTO {@link PlayerUpdateDTO}
     *
     * @param playerID  id of the player to be updated
     * @param updateDTO DTO to transfer data {@link PlayerUpdateDTO}
     * @return PlayerRepresentDTO object in case of success
     */
    @PutMapping("/{playerID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updatePlayer(@PathVariable int playerID, @RequestBody PlayerUpdateDTO updateDTO, WebRequest request) {
        log.info("[PUT] Request to 'updatePlayer'");
        try {
            updateDTO.setId(playerID);
            Player player = playerService.updatePlayer(updateDTO);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(player.getId())
                    .toUri();

            log.info("[PUT] Request to 'updatePlayer': PLayer is updated");
            return ResponseEntity.created(location).body(PlayerRepresentDTO.from(player));
        } catch (Exception e) {
            log.error("Error in method 'updateTeam': " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), request));
        }
    }

    /**
     * Method that deleting player
     *
     * @param playerID id of the player to be updated
     * @return PlayerDeletedDTO object in case of success
     */
    @DeleteMapping("/{playerID}")
    public ResponseEntity<?> deletePlayer(@PathVariable int playerID) {
        log.info("[DELETE] Request to 'deletePlayer'");

        PlayerDeletedDTO dto = playerService.deletePlayer(playerID);

        log.info("[DELETE] Request to 'deletePlayer': Player is deleted");
        return ResponseEntity.status(HttpStatus.OK).body(dto);

    }
}
