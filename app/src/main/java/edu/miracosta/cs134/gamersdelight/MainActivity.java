package edu.miracosta.cs134.gamersdelight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

import edu.miracosta.cs134.gamersdelight.model.Game;
import edu.miracosta.cs134.gamersdelight.model.JSONLoader;

public class MainActivity extends AppCompatActivity {


    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;

    private EditText nameEditText;
    private EditText descriptionEditText;
    private RatingBar gameRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Connect the ListView with the layout
        // TODO:  Populate all games list using the JSONLoader
        // TODO:  Create a new ListAdapter connected to the correct layout file and list
        // TODO:  Connect the ListView with the ListAdapter

        gamesListView = findViewById(R.id.gameListView);
        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        gameRatingBar = findViewById(R.id.gameRatingBar);

        try {
            gamesList = JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gamesListAdapter = new GameListAdapter(this, R.layout.game_list_item, gamesList);
        gamesListView.setAdapter(gamesListAdapter);
    }

    public void viewGameDetails(View view) {

        // TODO: Use an Intent to start the GameDetailsActivity with the data it needs to correctly inflate its views.
        Game clickedGame = (Game) view.getTag();

        Intent intent = new Intent(this, GameDetailsActivity.class);

        intent.putExtra("Name", clickedGame.getName());
        intent.putExtra("Description", clickedGame.getDescription());
        intent.putExtra("Rating", clickedGame.getRating());
        intent.putExtra("ImageName", clickedGame.getImageName());

        startActivity(intent);
    }

    public void addGame(View view)
    {
        // TODO:  Read information from EditTexts and RatingBar,
        // TODO:  Create a new Game object then add it to the list
        // TODO:  Make sure the list adapter is notified
        // TODO:  Clear all entries the user made (edit text and rating bar)

        String gameName = nameEditText.getText().toString();
        String gameDescription = descriptionEditText.getText().toString();
        float gameRating = gameRatingBar.getRating();

        if (gameName.isEmpty() || gameDescription.isEmpty() || gameRating == 0)
            Toast.makeText(this, "Please enter all game info", Toast.LENGTH_SHORT).show();
        else
        {
            Game newGame = new Game(gameName, gameDescription, gameRating);

            gamesList.add(newGame);
            gamesListAdapter.notifyDataSetChanged();

            nameEditText.setText("");
            descriptionEditText.setText("");
            gameRatingBar.setRating(0.0f);
        }
    }



}
