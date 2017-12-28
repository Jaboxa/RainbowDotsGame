package jessi.rainbowdots2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class YouLostActivity extends AppCompatActivity {

    Button tryAgainButton, submitNameButton;
    long score;
    String name;
    TextView youLostTextView;
    EditText nameEditText;
    private DataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_lost);
        db = new DataBaseHandler(this);
        db.open();

        youLostTextView = (TextView) findViewById(R.id.YouLostTextView);
        tryAgainButton = (Button) findViewById(R.id.tryAgainButton  );
        submitNameButton = (Button) findViewById(R.id.submitButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            score = bundle.getLong("ScoreFirstLevel");
            String scoreText = "Nice try! Your score is: " + score;
            youLostTextView.setText(scoreText);
        }

        submitNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = String.valueOf(nameEditText.getText());
                db.addScore(new User(name, score));
                Intent intent = new Intent(YouLostActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YouLostActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
