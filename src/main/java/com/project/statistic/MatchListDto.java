package com.project.statistic;

import io.vavr.collection.List;

import lombok.Data;

@Data
class MatchListDto {
    private List<MatchReferenceDto> matches;
    private Integer endIndex;
    private Integer startIndex;
    private Integer totalGames;
}

@Data
class MatchReferenceDto {
    private String lane;
    private Long gameId;
    private Integer champion;
    private String platformId;
    private Integer season;
    private Integer queue;
    private String role;
    private Long timestamp;
}