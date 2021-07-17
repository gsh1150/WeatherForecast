package com.example.weatherforecast.bean;

public class Now {

    private String cond_txt;
    private String tmp;
    private String wind_dir;
    private String wind_sc;

    public void setCond_txt(String cond_txt) {
        this.cond_txt = cond_txt;
    }

    public String getCond_txt() {
        return cond_txt;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getTmp() {
        return tmp;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_sc(String wind_sc) {
        this.wind_sc = wind_sc;
    }

    public String getWind_sc() {
        return wind_sc;
    }
}