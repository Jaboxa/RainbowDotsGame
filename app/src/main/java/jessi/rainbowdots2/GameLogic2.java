package jessi.rainbowdots2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class GameLogic2 extends View implements View.OnTouchListener {

    Paint paintEasy = new Paint();
    Paint paintRect= new Paint();
    List<Point> pointListEasy = new ArrayList<>();
    Integer[][] colorArrayEasy = new Integer[999][4];
    Bitmap snapshot2;
    float myHeight;
    float myWidth;
    float myRadie;

    public GameLogic2(Context context) {
        super(context);
        this.setOnTouchListener(this);

        for (int i = 0; i < 999; i++) {
            for (int j = 0; j < 4; j++) {
                int random = (int) (Math.random() * 254);
                colorArrayEasy[i][j] = random;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        myHeight = getHeight();
        myWidth = getWidth();

        if (myWidth < myHeight)
            myRadie = myWidth / 2;
        else
            myRadie = myHeight / 2;

        for (int i = 0; i < pointListEasy.size(); i++) {
            paintEasy.setARGB(255, colorArrayEasy[i][1], colorArrayEasy[i][2], colorArrayEasy[i][3]);
            canvas.drawCircle(pointListEasy.get(i).x, pointListEasy.get(i).y, myRadie / 3 , paintEasy);
        }
        canvas.drawRect(myHeight/2,0,getRight(),getBottom(),paintRect);
        canvas.drawRect(0,(myWidth*3/4),getRight(),getBottom(),paintRect);
        paintRect.setColor(Color.BLACK);
        paintEasy.setTextSize(myWidth/30);
        canvas.drawText("Fill the box with rainbow dots",myWidth/10,myHeight*2/3,paintEasy);
        canvas.drawText("before the time is up!",myWidth/10,myHeight*2/3+50,paintEasy);
        canvas.drawText("You have 15s!",myWidth/10,myHeight*2/3+100,paintEasy);
    }
    public Bitmap takeScreenShot(GameLogic2 gameLogic2) {

        gameLogic2.setDrawingCacheEnabled(true);
        gameLogic2.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        gameLogic2.buildDrawingCache();

        if(gameLogic2.getDrawingCache() == null) return null;

        snapshot2 = Bitmap.createBitmap(gameLogic2.getDrawingCache());
        gameLogic2.setDrawingCacheEnabled(false);
        gameLogic2.destroyDrawingCache();

        return snapshot2;
    }
    public boolean gameLost() {
        takeScreenShot(this);
        boolean gameLost = false;
        int R1, G1, B1;
        for (int i = 1; i <= (myHeight/2); i++) {
            for (int j = 1; j <= (myWidth*3/4) ; j++) {
                int pixel = snapshot2.getPixel(i, j);

                R1 = Color.red(pixel);
                G1 = Color.green(pixel);
                B1 = Color.blue(pixel);

                if ((R1 == 255) && (G1 == 255) && (B1 == 255))
                {
                    gameLost = true;
                }
            }
        }
        return gameLost;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Point p=new Point();
        p.x = (int) event.getX();
        p.y = (int) event.getY();
        pointListEasy.add(p);
        invalidate();
        return false;
    }
}
