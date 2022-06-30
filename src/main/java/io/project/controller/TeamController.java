package io.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	@GetMapping("/team")
    public Iterable<Team> getAllTeam() {
        return this.teamRepository.findAll();
    }
	
	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = teamRepository.findByTeamName(teamName);
		System.out.println(team);
		team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));
		return team;
	}
	
	@GetMapping("/team/{teamName}/matches")
	public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year+1, 1, 1);
		return matchRepository.getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName, startDate, endDate, teamName, startDate, endDate);
	}
	
//	@GetMapping("/team/{teamName}/matches")
//    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
//        LocalDate startDate = LocalDate.of(year, 1, 1);
//        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
//        return this.matchRepository.getMatchesByTeamBetweenDates(
//            teamName,
//            startDate,
//            endDate
//            );
//    }
}