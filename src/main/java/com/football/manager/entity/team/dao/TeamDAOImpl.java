package com.football.manager.entity.team.dao;

import com.football.manager.entity.player.Player;
import com.football.manager.entity.player.service.PlayerService;
import com.football.manager.entity.team.Team;
import com.football.manager.entity.team.dto.TeamDeletedDTO;
import com.football.manager.exception.EntityAlreadyExistException;
import com.football.manager.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class TeamDAOImpl implements TeamDAO {

    private final HibernateTemplate hibernateTemplate;
    private final PlayerService playerService;

    @Autowired
    public TeamDAOImpl(HibernateTemplate hibernateTemplate, PlayerService playerService) {
        this.hibernateTemplate = hibernateTemplate;
        this.playerService = playerService;
    }

    @Override
    public List<Team> getTeams() {
        List<Team> teams = hibernateTemplate.loadAll(Team.class);
        if (teams.size() == 0)
            return new ArrayList<>();
        else
            return hibernateTemplate.loadAll(Team.class);
    }

    @Override
    public Team saveTeam(Team teamToBeSaved) {
        Query<Team> query = hibernateTemplate
                .getSessionFactory()
                .getCurrentSession().createQuery("from Team u where u.clubName=:clubName", Team.class);
        query.setParameter("clubName", teamToBeSaved.getClubName());
        Team byName = query.uniqueResult();
        if (byName != null)
            throw new EntityAlreadyExistException("Team with the clubName '" + byName.getClubName() + "'" + " is already exist");
        else
            hibernateTemplate.persist(teamToBeSaved);

        return teamToBeSaved;
    }

    @Override
    public Team getTeam(int id) {
        Team team = hibernateTemplate.get(Team.class, id);
        if (team != null)
            return team;
        else
            throw new EntityNotFoundException("Team with id '" + id + "'" + " is not found");
    }

    @Override
    public Team getTeamByClubName(String clubName) {
        Query<Team> query = Objects.requireNonNull(hibernateTemplate
                        .getSessionFactory())
                .getCurrentSession().createQuery("from Team u where u.clubName=:clubName", Team.class);
        query.setParameter("clubName", clubName);
        Team byName = query.uniqueResult();
        if (byName == null)
            throw new EntityAlreadyExistException("Team with the clubName '" + clubName + "'" + " is already exist");
        else
            return byName;
    }

    @Override
    public TeamDeletedDTO deleteTeam(int id) {
        Team team = hibernateTemplate.get(Team.class, id);
        if (null != team) {
            TeamDeletedDTO dto = TeamDeletedDTO.from(team);
            List<Player> allPLayers = playerService.getPlayers();
            allPLayers.forEach(x -> {
                x.setMotherClub(null);
                x.getClubsPlayed().remove(team);
            });
            hibernateTemplate.delete(team);
            return dto;
        } else
            throw new EntityNotFoundException("Team with id '" + id + "'" + " is not found");
    }

}