package com.phonepe.repository;

import com.phonepe.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Long> {

    Team findByGameIdAndName(Long gameId, String name);
}
