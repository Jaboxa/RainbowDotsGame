package jessi.rainbowdots2;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameLogic2Activity extends AppCompatActivity {
    MyCountDown myCountDown;
    static final long startTime = 20000;
    static final long interval = 1000;
    GameLogic2 gameLogic2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameLogic2 = new GameLogic2(this);
        gameLogic2.setBackgroundColor(Color.rgb(255,255,255));
        setContentView(gameLogic2);

        myCountDown = new MyCountDown(startTime, interval);
        myCountDown.start();
    }

    // inner class
    private class MyCountDown extends CountDownTimer {
        MyCountDown(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            if (gameLogic2.gameLost()) {
                final long ScoreFirstLevel = 20;
                Intent intent = new Intent(GameLogic2Activity.this, YouLostActivity.class);
                intent.putExtra("ScoreFirstLevel",ScoreFirstLevel);
                startActivity(intent);
            }
            else {
                final long Scorewinner = 30;
                Intent intent = new Intent(GameLogic2Activity.this, YouWonActivity.class);
                intent.putExtra("Scorewinner",Scorewinner);
                startActivity(intent);
                startActivity(intent);
            }
        }


    }


}
