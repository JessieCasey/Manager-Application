package com.football.manager.entity.player.dao;

import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.dto.PlayerDeletedDTO;
import com.football.manager.entity.team.Team;
import com.football.manager.exception.EntityAlreadyExistException;
import com.football.manager.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.football.manager.entity.player.service.PlayerService.getCurrentTeamId;

/**
 * The PlayerDAOImpl class implements PlayerDAO interface to create methods {@link PlayerDAO}
 */

@Repository
@Slf4j
public class PlayerDAOImpl implements PlayerDAO {

    private final HibernateTemplate hibernateTemplate;

    @Autowired
    public PlayerDAOImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> teams = hibernateTemplate.loadAll(Player.class);
        if (teams.size() == 0)
            return new ArrayList<>();
        else
            return hibernateTemplate.loadAll(Player.class);
    }

    @Override
    public Player savePlayer(Player teamToBeSaved) {
        Query<Player> query = hibernateTemplate
                .getSessionFactory()
                .getCurrentSession().createQuery("from Player u where u.firstName=:firstName AND u.lastName=:lastname", Player.class);
        query.setParameter("firstName", teamToBeSaved.getFirstName());
        query.setParameter("lastname", teamToBeSaved.getLastName());
        Player byName = query.uniqueResult();

        if (byName != null)
            throw new EntityAlreadyExistException("Player with the nickname '" + byName.getFirstName() + "'" + " is already exist");
        else
            hibernateTemplate.persist(teamToBeSaved);
        return teamToBeSaved;
    }

    @Override
    public Player getPlayer(int id) {
        Player player = hibernateTemplate.get(Player.class, id);
        if (player != null)
            return player;
        else
            throw new EntityNotFoundException("Player with id '" + id + "'" + " is not found");
    }

    @Override
    public PlayerDeletedDTO deletePlayer(int id) {
        Player playerToBeDeleted = hibernateTemplate.get(Player.class, id);
        if (null != playerToBeDeleted) {
            PlayerDeletedDTO dto = PlayerDeletedDTO.from(playerToBeDeleted);

            Team currentTeam = hibernateTemplate.get(Team.class, getCurrentTeamId(playerToBeDeleted));
            currentTeam.getPlayers().remove(playerToBeDeleted);

            hibernateTemplate.delete(playerToBeDeleted);
            return dto;
        } else
            throw new EntityNotFoundException("Player with id '" + id + "'" + " is not found");
    }

}