package com.phonepe.repository;

import com.phonepe.model.ScoreDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScoreDetailsRepository extends CrudRepository<ScoreDetails,Long> {

    @Query(value = "select sum(score) from score_details where batsmen_name = :playerName and team_id = :teamId and valid_ball = '1' ",nativeQuery = true)
    Long getScoreOfPlayer(@Param("playerName") String playerName, @Param("teamId") Long teamId);

    @Query(value = "select count(*) from score_details where batsmen_name = :playerName and team_id = :teamId and score = :run",nativeQuery = true)
    Long getCountOfRunsForPlayer(@Param("playerName") String playerName, @Param("teamId") Long teamId,@Param("run") int run);
}
