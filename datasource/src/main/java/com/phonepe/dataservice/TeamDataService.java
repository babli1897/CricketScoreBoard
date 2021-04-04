package com.phonepe.dataservice;

import com.phonepe.model.Team;
import com.phonepe.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamDataService {

    @Autowired
    private TeamRepository teamRepository;

    public void saveTeamDetails(Team team)
    {
        teamRepository.save(team);
    }

    public Team findByGameIdAndTeamName(Long gameId, String teamName)
    {
        return teamRepository.findByGameIdAndName(gameId,teamName);
    }
}
