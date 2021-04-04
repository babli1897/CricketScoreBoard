package com.phonepe.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "ball")
public class Ball {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "over_no")
    private int overNumber;

    @Column(name = "score")
    private int score;

    @Column(name = "wide")
    private boolean wide;

    @Column(name = "no_ball")
    private boolean noBall;

    @Column(name = "wicket")
    private boolean wicket;

    @Column(name = "batsmen_player_id")
    private long batsMenPlayerId;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;
}
