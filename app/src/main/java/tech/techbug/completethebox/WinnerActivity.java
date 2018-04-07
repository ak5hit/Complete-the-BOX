package tech.techbug.completethebox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    private String p1Name, p2Name;
    private int p1Score, p2Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        //Retrieving parent intent and extracting winners name from it
        //than setting it to the text view
        Intent winnerIntent = getIntent();
        String winner = winnerIntent.getStringExtra("winnerName");
        p1Name = winnerIntent.getStringExtra("p1Name");
        p2Name = winnerIntent.getStringExtra("p2Name");
        p1Score = winnerIntent.getIntExtra("p1Score", 0);
        p2Score = winnerIntent.getIntExtra("p2Score", 0);
        boolean draw = winnerIntent.getBooleanExtra("draw", false);
        TextView winnerTextView = findViewById(R.id.winnerName);
        TextView p1ScoreTextView = findViewById(R.id.p1Score);
        TextView p2ScoreTextView = findViewById(R.id.p2Score);
        if(!draw) {
            winnerTextView.setText(winner);
            p1ScoreTextView.setText(Integer.toString(p1Score));
            p2ScoreTextView.setText(Integer.toString(p2Score));
        }
        else {
            winnerTextView.setText(winner);
            TextView wins = findViewById(R.id.winsTextView);
            wins.setVisibility(View.INVISIBLE);
            p1ScoreTextView.setText(Integer.toString(p1Score));
            p2ScoreTextView.setText(Integer.toString(p2Score));
        }

        //Adding functionality on Play Again button
        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WinnerActivity.this, Game34Activity.class);
                intent.putExtra("p1Name", p1Name);
                intent.putExtra("p2Name", p2Name);
                startActivity(intent);
                finish();
            }
        });

        //Adding functionality on Home button
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WinnerActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
