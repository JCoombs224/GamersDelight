package edu.miracosta.cs134.gamersdelight;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class GameDetailsActivity extends AppCompatActivity {

    private ImageView gameDetailsImageView;
    private TextView gameDetailsNameTextView;
    private TextView gameDetailsDescriptionTextView;
    private RatingBar gameDetailsRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        // TODO:  Use the Intent object sent from MainActivity to populate the Views on
        // TODO:  this layout, including the TextViews, RatingBar and ImageView with the Game details.

        Intent intent = getIntent();

        gameDetailsImageView = findViewById(R.id.gameDetailsImageView);
        gameDetailsNameTextView = findViewById(R.id.gameDetailsNameTextView);
        gameDetailsDescriptionTextView = findViewById(R.id.gameDetailsDescriptionTextView);
        gameDetailsRatingBar = findViewById(R.id.gameDetailsRatingBar);

        String name = intent.getStringExtra("Name");
        String description = intent.getStringExtra("Description");
        String imageName = intent.getStringExtra("ImageName");
        float rating = intent.getFloatExtra("Rating", 0.0f);

        gameDetailsNameTextView.setText(name);
        gameDetailsDescriptionTextView.setText(description);
        gameDetailsRatingBar.setRating(rating);

        AssetManager am = this.getAssets();
        try
        {
            InputStream stream = am.open(imageName);
            Drawable image = Drawable.createFromStream(stream, name);
            gameDetailsImageView.setImageDrawable(image);
        }
        catch (IOException e)
        {
            Log.e("Gamers Delight", "Error Loading image", e);
        }
    }
}
