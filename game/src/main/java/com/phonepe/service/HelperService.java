package com.phonepe.service;

import com.phonepe.dataservice.BallDataService;
import com.phonepe.dataservice.PlayerDataService;
import com.phonepe.dataservice.ScoreDetailsDataService;
import com.phonepe.dto.TeamResults;
import com.phonepe.model.Player;
import com.phonepe.model.Team;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.phonepe.Constants.*;

@Service
public class HelperService {

    @Autowired
    private  PlayerDataService playerDataService;

    @Autowired
    private ScoreDetailsDataService scoreDetailsDataService;

    @Autowired
    private BallDataService ballDataService;

    boolean isWideBall(String ballType)
    {
        return wideBall.equals(ballType);
    }

    boolean isFour(String ball)
    {
        return four.equals(ball);
    }

    boolean isSix(String ball)
    {
        return six.equals(ball);
    }

    boolean isNoBall(String ball)
    {
        return noBall.equals(ball);
    }

    boolean isWicket(String ball)
    {
        return wicket.equals(ball);
    }

    boolean isValidBall(String ball)
    {
        if(isNoBall(ball) || isWideBall(ball))
            return false;
        return true;
    }

    int getScore(String ball)
    {
        if(isWicket(ball))
            return 0;
        if(isWideBall(ball))
            return 1;
        return Integer.parseInt(ball);
    }

    void declareWinner(TeamResults scoreRunTeam1, TeamResults scoreRunTeam2)
    {
        int team1Score = scoreRunTeam1.getScore(), team2Score = scoreRunTeam2.getScore();
        if(team2Score>team1Score && !scoreRunTeam2.isWonByRun())
        {
            System.out.println("Result: Team 2 won the match by "+ scoreRunTeam2.getWicketsRemaining()+" wickets");
        }

        else if(team1Score>team2Score)
        {
            System.out.println("Result: Team 1 won the match by "+ (team1Score-team2Score)+" runs");
        }
        else if(team2Score>team1Score)
        {
            System.out.println("Result: Team 2 won the match by "+ (team2Score-team1Score)+" runs");
        }
        else
            System.out.println("Result: It's a tie, both the teams have scored "+(team1Score)+" runs");
    }

    List<String> getPlayerNames(String battingOrder)
    {
        return Arrays.asList(battingOrder.split(","));
    }

    Player getNextPlayer(int index, List<String> playerNames, Long teamId)
    {
        if(index>playerNames.size()-1)
            return null;

        return playerDataService.findByTeamAndName(teamId,playerNames.get(index));
    }

    Pair<String,List<Player>> setBattingOrder(Team team) throws Exception
    {
        List<String> battingOrder = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Batting Order for "+team.getName()+":");
        List<Player> players = new ArrayList<>();
        for(int i=0;i<team.getNoOfPlayers();i++)
        {
            String playerName = br.readLine();
            battingOrder.add(playerName);
            Player player = new Player();
            player.setGameId(team.getGameId());
            player.setName(playerName);
            player.setPlaying(false);
            players.add(player);
        }
        return Pair.with(String.join(",",battingOrder),players);
    }

    void printScoreCard(int overNumber, Team team, int totalScore, int wicket, int noOfBallsInOver)
    {
        System.out.println("Score Card for "+team.getName()+":");
        StringBuilder output = new StringBuilder();
        output.append(String. format("%-20s", playerName));
        output.append(String.format("%-20s",score));
        output.append(String.format("%-20s",fours));
        output.append(String.format("%-20s",sixes));
        output.append(String.format("%-20s",balls));
        System.out.println(output.toString());
        List<Player> players = playerDataService.findByTeamId(team.getId());
        for(Player player : players)
        {
            StringBuilder playerScoreBoard = new StringBuilder();
            playerScoreBoard.append(String.format("%-20s",player.isPlaying()?player.getName()+"*":player.getName()));
            playerScoreBoard.append(String.format("%-20s",scoreDetailsDataService.getScoreForPlayer(player.getName(),team.getId())));
            playerScoreBoard.append(String.format("%-20s",scoreDetailsDataService.getFoursForPlayer(player.getName(),team.getId())));
            playerScoreBoard.append(String.format("%-20s",scoreDetailsDataService.getSixesForPlayer(player.getName(),team.getId())));
            playerScoreBoard.append(String.format("%-20s",ballDataService.getCountOfBallsPlayedByPlayer(team.getGameId(),player.getId())));
            System.out.println(playerScoreBoard);
        }
        System.out.println("Total: "+ totalScore+"/"+wicket);
        System.out.println("Overs: "+((noOfBallsInOver==0)?overNumber:(overNumber-1)+"."+(noOfBallsPerOver-noOfBallsInOver)));

    }
}

