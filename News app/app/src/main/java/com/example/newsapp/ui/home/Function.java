import android.content.Context;
import android.net.ConnectivityManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//implements functions that check if a network connection available, and gathering execture URL handling

public class Function {

    //checks that there is a netowrk connection or that can be connected to
    public static boolean networkAvailability(Context connect){
        return ((ConnectivityMangaer) connect.getSystemService(connect.CONNECTIVITY_SERVICE)).getActiveNetwrokInfo() != null;
    }



    //Tests connection to URL and if good opens connection to an input stream gathering json data from target URL
    public static String infoGet(String urlTarget){
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlTarget);
            connection = (HttpURLConnection) url.openConnection();
            //sets the type of information gathered and the character set to be used when gathering as set by api
            connection.setRequestProperty("content-type", "application/json; charset=utf-8");
            //Sets language that the information pulled will be in
            connectin.SetRequestProperty("content-Language", "en-US");
            //sets parameters for what we are doing. Not using cache info, gathering input from URL and not outputting to it
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);

            InputStream istream;
            //tests that connection is good before opening input stream. If it is not good give error
            int staus = connection.getResponseCode();

            if (staus != HttpURLConnection.HTTP_OK) {
                istream = connection.getErrorStream();
            } else {
                istream = connection.getInputStream();
            }

            //Sets up a buffered reader for effecient reading of json info

            BufferedReader read = new BufferedREader(new InputStreamREader(istream));
            String input;
            StringBuffer response = new StringBuffer();
            while ((input = read.readLines()) != null) {
                respnose.append(input);
                response.append('\r');

            }
            read.closer();
            return response.toString();
          //if something failed catch the error and return null
        } catch(Exception e) {
            return null;
          //if the connection is still open close it
        } finally {
            if (connection !=null) {
                connectin.disconnect();
            }
            }
        }
    }
