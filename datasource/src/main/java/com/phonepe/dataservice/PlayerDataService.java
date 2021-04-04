package com.phonepe.dataservice;

import com.phonepe.model.Player;
import com.phonepe.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerDataService {

    @Autowired
    private PlayerRepository playerRepository;

    public void savePlayers(List<Player> players)
    {
        playerRepository.saveAll(players);
    }

    public void updatePlayer(Player player)
    {
        playerRepository.save(player);
    }
    public Player findByTeamAndName(Long teamId, String name)
    {
        return playerRepository.findByTeamIdAndName(teamId,name);
    }

    public List<Player> findByTeamId(Long teamId)
    {
        return playerRepository.findByTeamIdOrderByIdAsc(teamId);
    }
}
