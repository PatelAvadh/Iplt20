package io.project.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import io.project.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchDataIngestion, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchDataIngestion matchDataIngestion) throws Exception {
    Match match = new Match();
    
    match.setId(Long.parseLong(matchDataIngestion.getId()));
    match.setCity(matchDataIngestion.getCity());
    match.setDate(LocalDate.parse(matchDataIngestion.getDate()));
    match.setPlayerOfMatch(matchDataIngestion.getPlayer_of_match());
    match.setVenue(matchDataIngestion.getVenue());
    
    // Set tram1 and team2 depending on the innings order
    String firstInningsTeam, secondInningsTeam;
    
    if("bat".equals(matchDataIngestion.getToss_decision())) {
    	firstInningsTeam = matchDataIngestion.getToss_winner();
    	secondInningsTeam = matchDataIngestion.getToss_winner().equals(matchDataIngestion.getTeam1()) ? matchDataIngestion.getTeam2() : matchDataIngestion.getTeam1() ;
    } else {
    	secondInningsTeam = matchDataIngestion.getToss_winner();
    	firstInningsTeam = matchDataIngestion.getToss_winner().equals(matchDataIngestion.getTeam1()) ? matchDataIngestion.getTeam2() : matchDataIngestion.getTeam1() ;
    }
    
    match.setTeam1(firstInningsTeam);
    match.setTeam2(secondInningsTeam);
    match.setTossWinner(matchDataIngestion.getToss_winner());
    match.setTossDecision(matchDataIngestion.getToss_decision());
    match.setMatchWinner(matchDataIngestion.getWinner());
    match.setResult(matchDataIngestion.getResult());
    match.setResultMargin(matchDataIngestion.getResult_margin());
    match.setUmpire1(matchDataIngestion.getUmpire1());
    match.setUmpire2(matchDataIngestion.getUmpire2());
    
    return match; 
  }

}
