package com.youngdredstudios.fmtracker.models;

import com.google.type.DateTime;

public class Competition {
    public String Name;
    public String Trophy;
    public String Type;
    public String CountryCode;

    public Competition(String name, String trophy, String type, String countryCode) {
        Name = name;
        Trophy = trophy;
        Type = type;
        CountryCode = countryCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTrophy() {
        return Trophy;
    }

    public void setTrophy(String trophy) {
        Trophy = trophy;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }
}
