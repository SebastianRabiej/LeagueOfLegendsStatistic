package com.project.statistic;

import lombok.Data;

import java.util.List;

@Data
class MatchDto {

    private long gameCreation;
    private long gameDuration;
    private long gameId;
    private String gameMode;
    private String gameType;
    private String gameVersion;
    private int mapId;
    private List<ParticipantIdentity> participantIdentities;
    private List<Participant> participants;
    private String platformId;
    private int queueId;
    private int seasonId;
    private List<TeamStats> teams;
}