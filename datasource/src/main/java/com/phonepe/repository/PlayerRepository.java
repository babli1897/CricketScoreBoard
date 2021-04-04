package com.phonepe.repository;

import com.phonepe.model.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player,Long> {

    Player findByTeamIdAndName(Long teamId, String name);

    List<Player> findByTeamIdOrderByIdAsc(Long teamId);
}
