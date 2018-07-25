package com.apress.gerber.currencies;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONParser {

    static InputStream sInputStream = null;
    static JSONObject sReturnJsonObject = null;
    static String sRawJsonString = "";

    public JSONParser(){ }

    public JSONObject getJSONFromUrl(String url){

        //attempt to get response from server
        try {
            HttpURLConnection connection = null;
            URL conn_url = new URL(url);
            connection = (HttpURLConnection) conn_url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            sInputStream = connection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //read Stream into string-builder
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(sInputStream, "iso-8859-1"), 8);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            sInputStream.close();
            sRawJsonString = stringBuilder.toString();
        }catch (Exception e){
            Log.e("Error read from Buffer:" + e.toString(), this.getClass().getSimpleName());
        }

        try {
            sReturnJsonObject = new JSONObject(sRawJsonString);
        } catch (JSONException e) {
            Log.e("Parser", "Error when parsing data " + e.toString());
        }

        //return json object
        return sReturnJsonObject;
    }
}
