package jessi.rainbowdots2;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameLogic1Activity extends AppCompatActivity {

    MyCountDown myCountDown;
    static final long startTime = 25000;
    static final long interval = 1000;
    GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameLogic = new GameLogic(this);
        gameLogic.setBackgroundColor(Color.rgb(255,255,255));
        setContentView(gameLogic);
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
            if (gameLogic.gameLost()) {

                final long ScoreFirstLevel = 10;
                Intent intent = new Intent(GameLogic1Activity.this, YouLostActivity.class);
                intent.putExtra("ScoreFirstLevel",ScoreFirstLevel);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(GameLogic1Activity.this, GameLogic2Activity.class);
                startActivity(intent);
            }
        }


    }

}
