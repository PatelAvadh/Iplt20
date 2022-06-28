package io.project.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.project.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

	Team findByTeamName(String teamName);
	
	
}
