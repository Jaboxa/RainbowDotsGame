package jessi.rainbowdots2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class YouWonActivity extends AppCompatActivity {

    TextView youWonTextView, scoreTextView;
    Button playAgainButton;
    long score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_won);

        youWonTextView=(TextView)findViewById(R.id.textView);
        scoreTextView=(TextView)findViewById(R.id.textViewscore);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            score= bundle.getLong("Scorewinner");
            String scoreText="Yay! That's all the levels I have right now. Your score is: "+score;
            scoreTextView.setText(scoreText);
        }

        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(YouWonActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
