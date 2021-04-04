package com.phonepe.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "score_details")
public class ScoreDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batsmen_name")
    private String batsMenName;

    @Column(name = "score")
    private int score;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "ball_id")
    private Long ballId;

    @Column(name = "valid_ball")
    private boolean validBall;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;
}
