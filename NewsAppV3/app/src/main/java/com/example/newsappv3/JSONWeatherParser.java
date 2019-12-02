package com.example.newsappv3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONWeatherParser {
    public static WeatherConditions getWeather(String data) throws JSONException {
        WeatherConditions w = new WeatherConditions();
        System.out.println("Data ["+data+"]");
// We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);

// We start extracting the info
        Location loc = new Location();

        JSONObject coordObj = getObject("coord", jObj);
        loc.setLatitude(getFloat("lat", coordObj));
        loc.setLongitude(getFloat("lon", coordObj));

        JSONObject sysObj = getObject("sys", jObj);
        loc.setCountry(getString("country", sysObj));
        loc.setSunrise(getInt("sunrise", sysObj));
        loc.setSunset(getInt("sunset", sysObj));
        loc.setCity(getString("name", jObj));
        w.location = loc;

// We get weather info (This is an array)
        JSONArray jArr = jObj.getJSONArray("weather");

// We use only the first value
        JSONObject JSONWeather = jArr.getJSONObject(0);
        w.currentCondition.setWeatherId(getInt("id", JSONWeather));
        w.currentCondition.setDescr(getString("description", JSONWeather));
        w.currentCondition.setCondition(getString("main", JSONWeather));
        w.currentCondition.setIcon(getString("icon", JSONWeather));

        JSONObject mainObj = getObject("main", jObj);
        w.currentCondition.setHumidity(getInt("humidity", mainObj));
        w.currentCondition.setPressure(getInt("pressure", mainObj));
        w.temperature.setMaxTemp(getFloat("temp_max", mainObj));
        w.temperature.setMinTemp(getFloat("temp_min", mainObj));
        w.temperature.setTemp(getFloat("temp", mainObj));

// Wind
        JSONObject wObj = getObject("wind", jObj);
        w.wind.setSpeed(getFloat("speed", wObj));
        w.wind.setDeg(getFloat("deg", wObj));

// Clouds
        JSONObject cObj = getObject("clouds", jObj);
        w.clouds.setPerc(getInt("all", cObj));

// We download the icon to show

        return w;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }
    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
