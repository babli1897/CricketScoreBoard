package com.phonepe;

import com.phonepe.model.Game;
import com.phonepe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    private GameService gameService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        Game game = gameService.initializeGame();
        System.out.println("Game Initialized with Id "+game.getId());
        gameService.startGame(game);
    }
}
