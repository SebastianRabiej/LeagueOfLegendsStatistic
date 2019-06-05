package com.project.statistic;

import lombok.Data;

import java.util.List;

@Data
class TeamStats {

    private List<TeamBans> bans;
    private int baronKills;
    private int dominionVictoryScore;
    private int dragonKills;
    private boolean firstBaron;
    private boolean firstBlood;
    private boolean firstDragon;
    private boolean firstInhibitor;
    private boolean firstRiftHerald;
    private boolean firstTower;
    private int inhibitorKills;
    private int riftHeraldKills;
    private int teamId;
    private int towerKills;
    private int vilemawKills;
    private String win;
}
