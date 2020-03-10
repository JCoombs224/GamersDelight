package edu.miracosta.cs134.gamersdelight.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class loads Game data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (Game) with data.
 */

public class JSONLoader {

    public static final String TAG = JSONLoader.class.getSimpleName();

    /**
     * Loads JSON data from a file in the assets directory.
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<Game> loadJSONFromAsset(Context context) throws IOException {
        List<Game> allGames = new ArrayList<>();
        String json;
            InputStream is = context.getAssets().open("Games.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        try {
            JSONObject jsonRootObject = new JSONObject(json);
            //DONE: Complete the name of the root object in the JSON file
            JSONArray allGamesJSON = jsonRootObject.getJSONArray("Games");
            int count = allGamesJSON.length();
            JSONObject gameJSON;
            String name, description, imageName;
            float rating;

            // DONE: Loop through the root object array and
            // DONE: Extract each single Game from the JSON file.
            // DONE: Create an object to represent each game, then
            // DONE: add each Game to the list.
            for (int i = 0; i < count; i++) {
                gameJSON = allGamesJSON.getJSONObject(i);
                name = gameJSON.getString("Name");
                description = gameJSON.getString("Description");
                imageName = gameJSON.getString("ImageName");
                rating = Float.parseFloat(gameJSON.getString("Rating"));

                allGames.add(new Game(name, description, rating, imageName));
            }
        }
        catch (JSONException e)
        {
            Log.e(TAG, e.getMessage());
        }

        return allGames;
    }
}
