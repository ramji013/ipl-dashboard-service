package com.ipl.dashboard.controller;

import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.Team;
import com.ipl.dashboard.repository.MatchRepository;
import com.ipl.dashboard.repository.TeamRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
public class TeamController {

    @Autowired
    private TeamRespository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team = teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));
        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year, 1,1);
        LocalDate endDate = LocalDate.of(year+1, 1,1);
        return matchRepository.getMatchesByTeamsBetweenDates(teamName,startDate, endDate);
    }

    @GetMapping("/team")
    public Iterable<Team> getAllTeam(){
        return teamRepository.findAll();
    }
}
