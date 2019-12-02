package com.example.newsappv3;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static String KEY = "28d98fa572c314d6b59084f6cb75fa6c";

    private static String BASE_FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&q=";

    public String getWeatherData(String location) {
        HttpURLConnection con = null;
        InputStream is = null;

        try {
            String url = BASE_URL + "Dallas,Texas";
            url = url +"&APPID="+KEY; //todo this might be broken, replace KEY with "28d98fa572c314d6b59084f6cb75fa6c"
            Log.d("Tag","URL "+url);
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);//try false
            con.setUseCaches(false);
            //con.connect();

            InputStream input;

            // Check that connection was made properly
            int status = con.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK)
                input = con.getErrorStream();
            else
                // if connection was successful then save data
                input = con.getInputStream();

// Let's read the response
            StringBuffer buffer = null;
            try {
                buffer = new StringBuffer();
                is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while ( (line = br.readLine()) != null )
                    buffer.append(line + "\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            is.close();
            con.disconnect();

            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        return null;

    }


    public String getForecastWeatherData(String location, String lang, String sForecastDayNum) {
        HttpURLConnection con = null ;
        InputStreamReader is = null;
        int forecastDayNum = Integer.parseInt(sForecastDayNum);

        try {

// Forecast
            String url = BASE_URL + location;
            url = url +"&APPID="+KEY; //todo this might be broken, replace KEY with "28d98fa572c314d6b59084f6cb75fa6c"
            //url = url + "&cnt=" + forecastDayNum+"&APPID=<YOUR API KEY>";
            con = (HttpURLConnection) ( new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            StringBuffer buffer1 = null;
            Log.d("Tag","connection "+con.toString());
            try {
// Let's read the response
                buffer1 = new StringBuffer();
                is = new InputStreamReader(con.getInputStream());//con.getInputStream();
                BufferedReader br1 = new BufferedReader(is);
                String line1 = null;
                while ( (line1 = br1.readLine()) != null )
                    buffer1.append(line1 + "\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            is.close();
            con.disconnect();

            System.out.println("Buffer ["+buffer1.toString()+"]");
            return buffer1.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        return null;

    }

    public byte[] getImage(String code) {
        HttpURLConnection con = null ;
        InputStream is = null;
        try {
            con = (HttpURLConnection) ( new URL(IMG_URL + code)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

// Let's read the response
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ( is.read(buffer) != -1)
                baos.write(buffer);

            return baos.toByteArray();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }
}
