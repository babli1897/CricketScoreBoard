package com.phonepe.dataservice;

import com.phonepe.model.ScoreDetails;
import com.phonepe.repository.ScoreDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreDetailsDataService {

    @Autowired
    private ScoreDetailsRepository scoreDetailsRepository;

    public void saveScoreDetails(ScoreDetails scoreDetails)
    {
        scoreDetailsRepository.save(scoreDetails);
    }

    public Long getScoreForPlayer(String playerName, Long teamId)
    {
        Long score = scoreDetailsRepository.getScoreOfPlayer(playerName,teamId);
        if(score==null)
            return 0l;
        return score;
    }

    public Long getFoursForPlayer(String playerName, Long teamId)
    {
        return scoreDetailsRepository.getCountOfRunsForPlayer(playerName,teamId,4);
    }

    public Long getSixesForPlayer(String playerName, Long teamId)
    {
        return scoreDetailsRepository.getCountOfRunsForPlayer(playerName,teamId,6);
    }
}
