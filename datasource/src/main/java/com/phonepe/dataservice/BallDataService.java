package com.phonepe.dataservice;

import com.phonepe.model.Ball;
import com.phonepe.repository.BallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BallDataService {

    @Autowired
    private BallRepository ballRepository;

    public void saveBallDetails(Ball ball)
    {
        ballRepository.save(ball);
    }

    public Long getCountOfBallsPlayedByPlayer( Long gameId, Long playerId)
    {
        return ballRepository.getCountOfBallsForPlayer(gameId,playerId);
    }
}
