package com.project.statistic;

import lombok.Data;

@Data
class Player {

    private String accountId;
    private String currentAccountId;
    private String currentPlatformId;
    private String matchHistoryUri;
    private String platformId;
    private int profileIcon;
    private String summonerId;
    private String summonerName;
}
