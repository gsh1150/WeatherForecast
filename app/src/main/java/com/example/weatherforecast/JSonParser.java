package com.example.weatherforecast;

import com.example.weatherforecast.bean.WeatherBean;
import com.google.gson.Gson;

public class JSonParser {
    public static WeatherBean parse(String jsonString) {
        WeatherBean weatherBean = new Gson().fromJson(jsonString, WeatherBean.class);
        return weatherBean;
    }
}
