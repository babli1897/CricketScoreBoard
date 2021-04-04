package com.phonepe.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team1_name")
    private String team1;

    @Column(name = "team2_name")
    private String team2;

    @Column(name = "no_of_overs")
    private int noOfOvers;

    @Column(name = "no_of_players_per_team")
    private int noOfPlayersPerTeam;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;
}
