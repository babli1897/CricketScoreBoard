package com.phonepe.dataservice;

import com.phonepe.model.Game;
import com.phonepe.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameDataService {

    @Autowired
    private GameRepository gameRepository;

    public Game saveGameDetails(Game game)
    {
        return gameRepository.save(game);
    }
}
