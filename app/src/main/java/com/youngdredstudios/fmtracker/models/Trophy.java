package com.youngdredstudios.fmtracker.models;

import com.google.type.DateTime;

public class Trophy {
    public String TeamId;
    public String CompetitionId;
    public String UserId;
    public DateTime WinningTime;
    public DateTime GameDay;

    public String getTeamId() {
        return TeamId;
    }

    public void setTeamId(String teamId) {
        TeamId = teamId;
    }

    public String getCompetitionId() {
        return CompetitionId;
    }

    public void setCompetitionId(String competitionId) {
        CompetitionId = competitionId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public DateTime getWinningTime() {
        return WinningTime;
    }

    public void setWinningTime(DateTime winningTime) {
        WinningTime = winningTime;
    }

    public DateTime getGameDay() {
        return GameDay;
    }

    public void setGameDay(DateTime gameDay) {
        GameDay = gameDay;
    }

    public Trophy(String teamId, String competitionId, String userId, DateTime winningTime, DateTime gameDay) {
        TeamId = teamId;
        CompetitionId = competitionId;
        UserId = userId;
        WinningTime = winningTime;
        GameDay = gameDay;
    }
}
