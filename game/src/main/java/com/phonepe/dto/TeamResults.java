package com.phonepe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamResults {

    private int score;

    private boolean wonByRun;

    private int wicketsRemaining;

}
