package com.phonepe.repository;

import com.phonepe.model.Ball;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BallRepository extends CrudRepository<Ball,Long> {

    @Query(value = "select count(*) from ball where game_id = :gameId and batsmen_player_id = :playerId and wide <> '1' and no_ball <> '1' ",nativeQuery = true)
    Long getCountOfBallsForPlayer(@Param("gameId") Long gameId, @Param("playerId") Long playerId);
}
