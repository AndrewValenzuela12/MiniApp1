package com.example.andrewvalenzuela.miniapp1;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


/**
 * Created by AndrewValenzuela on 2/13/18.
 */

public class Movie {
    // instance variables
    public String title;
    public String EpisodeNumber;
    public String MainCharacters;
    public String description;
    public String poster;
    public String URL;

    //methods that will  return an array list of movies constructed from the JSON file
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context) {
        ArrayList<Movie> movieList = new ArrayList<>();

        // try to read from the JSON file
        // get information by using the tags
        // construct a Movie Object for each movie in JSON
        // add the object to arrayList

        try {
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");


            // for loop to go through each movie in your movies array

            for (int i=0; i < movies.length(); i++){
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.EpisodeNumber = movies.getJSONObject(i).getString("episode_number");
                String[] characterarray  = movies.getJSONObject(i).getString("main_characters").split(",");
                String characterone = characterarray[0].replace("\"", "").replaceAll("[\\[\\](){}]", "");
                String charactertwo = characterarray[1].replace("\"", "");
                String characterthree = characterarray[2].replace("\"", "");
                movie.MainCharacters = characterone + ", " + charactertwo + ", " + characterthree;
                movie.description = movies.getJSONObject(i).getString("description");
                movie.poster = movies.getJSONObject(i).getString("poster");
                movie.URL = movies.getJSONObject(i).getString("url");

                // add to arrayList

                movieList.add(movie);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // return arrayList
        return movieList;
    }
    // helper method that loads from any JSON file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
