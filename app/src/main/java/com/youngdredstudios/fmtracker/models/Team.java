package com.youngdredstudios.fmtracker.models;

public class Team {
    public String TeamId;
    public String Badge;
    public String CountryCode;
    public String Name;

    public Team(String teamId, String badge, String countryCode, String name) {
        TeamId = teamId;
        Badge = badge;
        CountryCode = countryCode;
        Name = name;
    }

    public String getTeamId() {
        return TeamId;
    }

    public void setTeamId(String teamId) {
        TeamId = teamId;
    }

    public String getBadge() {
        return Badge;
    }

    public void setBadge(String badge) {
        Badge = badge;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
