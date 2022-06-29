package io.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.project.Repository.MatchRepository;
import io.project.Repository.TeamRepository;
import io.project.model.Match;
import io.project.model.Team;

@RestController
@CrossOrigin
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = teamRepository.findByTeamName(teamName);
		System.out.println(team);
		team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));
		return team;
	}
}
