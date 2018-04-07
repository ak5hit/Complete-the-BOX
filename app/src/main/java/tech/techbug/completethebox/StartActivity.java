package tech.techbug.completethebox;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private String p1Name, p2Name;
    Button startButton34, startButton45;
    TextInputEditText p1EditTV;
    TextInputEditText p2EditTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startButton34 = findViewById(R.id.startButton34);
        startButton45 = findViewById(R.id.startButton45);
        p1EditTV = findViewById(R.id.p1Name);
        p2EditTV = findViewById(R.id.p2Name);


        startButton34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent34 = new Intent(StartActivity.this, Game34Activity.class);
                p1Name = p1EditTV.getText().toString();
                p2Name = p2EditTV.getText().toString();
                if(p1Name.equals("") || p2Name.equals(""))
                    Toast.makeText(StartActivity.this, "Enter Name(s)",
                            Toast.LENGTH_SHORT).show();
                else {
                    intent34.putExtra("p1Name", p1Name);
                    intent34.putExtra("p2Name", p2Name);
                    startActivity(intent34);
                }
            }
        });

        startButton45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent45 = new Intent(StartActivity.this, Game45Activity.class);
                p1Name = p1EditTV.getText().toString();
                p2Name = p2EditTV.getText().toString();
                if(p1Name.equals("") || p2Name.equals(""))
                    Toast.makeText(StartActivity.this, "Enter Name(s)",
                            Toast.LENGTH_SHORT).show();
                else {
                    intent45.putExtra("p1Name", p1Name);
                    intent45.putExtra("p2Name", p2Name);
                    startActivity(intent45);
                }
            }
        });
    }
}
