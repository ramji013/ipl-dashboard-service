package com.ipl.dashboard.repository;

import com.ipl.dashboard.model.Team;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TeamRespository extends CrudRepository<Team, Long> {

    Team findByTeamName(String teamName);
}
