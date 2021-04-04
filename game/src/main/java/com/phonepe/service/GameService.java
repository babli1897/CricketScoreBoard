package com.phonepe.service;

import com.phonepe.Constants;
import com.phonepe.dataservice.*;
import com.phonepe.model.*;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static com.phonepe.Constants.*;

@Service
@Slf4j
public class GameService {

    @Autowired
    private PlayerDataService playerDataService;

    @Autowired
    private TeamDataService teamDataService;

    @Autowired
    private HelperService helperService;

    @Autowired
    private BallDataService ballDataService;

    @Autowired
    private ScoreDetailsDataService scoreDetailsDataService;

    @Autowired
    private GameDataService gameDataService;

    public Game initializeGame() throws Exception
    {
        Game cricketGame = new Game();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Number of Players in Each team:");
        Integer noOfPlayers = Integer.parseInt(br.readLine());
        cricketGame.setNoOfPlayersPerTeam(noOfPlayers);
        System.out.println("Number of Overs:");
        Integer noOfOvers= Integer.parseInt(br.readLine());
        cricketGame.setNoOfOvers(noOfOvers);
        cricketGame.setTeam1(team1);
        cricketGame.setTeam2(team2);
        cricketGame = gameDataService.saveGameDetails(cricketGame);
        initializeTeam(cricketGame,team1);
        initializeTeam(cricketGame,team2);
        return cricketGame;
    }

    public void startGame(Game game) throws Exception {
        Team team1 = teamDataService.findByGameIdAndTeamName(game.getId(), Constants.team1);
        Team team2 = teamDataService.findByGameIdAndTeamName(game.getId(), Constants.team2);
        createPlayers(team1);
        int team1Score = playTeam(game,team1);
        createPlayers(team2);
        int team2Score = playTeam(game, team2);
        helperService.declareWinner(team1Score, team2Score);
    }

    private void createPlayers(Team team) throws Exception {
        Pair<String,List<Player>> pair = helperService.setBattingOrder(team);
        team.setBattingOrder(pair.getValue0());
        teamDataService.saveTeamDetails(team);
        List<Player> players = pair.getValue1();
        players.stream().forEach(player -> {player.setTeamId(team.getId());});
        playerDataService.savePlayers(players);
    }

    private void initializeTeam(Game game, String teamName) throws Exception
    {
        Team team = new Team();
        team.setGameId(game.getId());
        team.setNoOfPlayers(game.getNoOfPlayersPerTeam());
        team.setName(teamName);
        teamDataService.saveTeamDetails(team);

    }



    private int playTeam(Game game, Team team) throws Exception
    {
        int noOfBallsInOver = noOfBallsPerOver, wicket = 0 , overNumber = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nextPlayer = 2, totalScore = 0;
        List<String> playerNames = helperService.getPlayerNames(team.getBattingOrder());
        Player playerInAction = playerDataService.findByTeamAndName(team.getId(),playerNames.get(0));
        Player otherPlayer = playerDataService.findByTeamAndName(team.getId(),playerNames.get(1));
        playerInAction.setPlaying(true);
        otherPlayer.setPlaying(true);
        playerDataService.savePlayers(Arrays.asList(playerInAction,otherPlayer));
        while (overNumber<=game.getNoOfOvers()) {
            System.out.println("Over "+overNumber+":");
            while (noOfBallsInOver > 0 && wicket < game.getNoOfPlayersPerTeam()) {
                String ball = br.readLine();
                if (helperService.isValidBall(ball))
                    noOfBallsInOver--;
                updateBallAndScore(game.getId(),ball, overNumber, team.getId(), playerInAction);
                int score = helperService.getScore(ball);
                totalScore+=score;
                if (helperService.isWicket(ball)) {
                    wicket++;
                    playerInAction.setPlaying(false);
                    playerDataService.updatePlayer(playerInAction);
                    playerInAction = helperService.getNextPlayer(nextPlayer, playerNames, team.getId());
                    if (playerInAction == null) {
                        helperService.printScoreCard(overNumber,team,totalScore,wicket,noOfBallsInOver);
                        return totalScore;
                    }
                    playerInAction.setPlaying(true);
                    playerDataService.updatePlayer(playerInAction);
                    nextPlayer++;
                }
                if (helperService.isValidBall(ball) && (score % 2 == 1)) {
                    Player tempPlayer = playerInAction;
                    playerInAction = otherPlayer;
                    otherPlayer = tempPlayer;
                }
                if(!helperService.isWicket(ball) &&  noOfBallsInOver==0)
                {
                    Player tempPlayer = playerInAction;
                    playerInAction = otherPlayer;
                    otherPlayer = tempPlayer;
                }
            }
            helperService.printScoreCard(overNumber,team,totalScore,wicket,noOfBallsInOver);
            noOfBallsInOver = 6;
            overNumber++;
        }
        return totalScore;
    }


    private void updateBallAndScore(Long gameId, String ballType, int overNumber, Long teamId, Player player)
    {
        Ball ball = new Ball();
        ball.setGameId(gameId);
        ball.setOverNumber(overNumber);
        ball.setNoBall(helperService.isNoBall(ballType));
        ball.setScore(helperService.getScore(ballType));
        ball.setWicket(helperService.isWicket(ballType));
        ball.setWide(helperService.isWideBall(ballType));
        ball.setBatsMenPlayerId(player.getId());
        ballDataService.saveBallDetails(ball);

        ScoreDetails scoreDetails = new ScoreDetails();
        scoreDetails.setBallId(ball.getId());
        scoreDetails.setScore(helperService.getScore(ballType));
        scoreDetails.setBatsMenName(player.getName());
        scoreDetails.setTeamId(teamId);
        scoreDetails.setValidBall(helperService.isValidBall(ballType));
        scoreDetailsDataService.saveScoreDetails(scoreDetails);
    }


}
