//////////////////////////////////
// @author Joshua Krasnogorov
// @version 2/8/2024
//////////////////////////////////

package com.example.facemaker;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Random;

public class Face extends SurfaceView {

    //instance variables
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;
    private int canvasWidthCenter;
    private int canvasHeightCenter;
    private Paint skinPaint = new Paint();
    private Paint eyePaint = new Paint();
    private Paint hairPaint = new Paint();
    private Paint whitePaint = new Paint();
    private double faceScaler = 1.4; // scales the face
    private final int hairRadio = 2131296612;
    private final int eyesRadio = 2131296611;
    private final int skinRadio = 2131296613;
    private int radioChecked;


    //instance setters
    public void setSkinColor(int skinColor) {
        this.skinColor = skinColor;
    }
    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }
    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }
    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
    }
    public void setRadioChecked(int radioChecked) {
        this.radioChecked = radioChecked;
    }


    //instance getters
    public int getSkinColor() {
        return skinColor;
    }
    public int getEyeColor() {
        return eyeColor;
    }
    public int getHairColor() {
        return hairColor;
    }
    public int getHairStyle() {
        return hairStyle;
    }
    public int getRadioChecked() {
        return radioChecked;
    }


    //Face constructor, automatically calls randomize method and allows surfaceView to draw
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        randomize();

        whitePaint.setColor(0xFFFFFFFF);

        this.setWillNotDraw(false);
    }

    //Randomizes instance variables
    public void randomize() {
        Random gen = new Random();

        skinColor = randomColor();
        eyeColor = randomColor();
        hairColor = randomColor();

        //randomize hairstyle to value between 0 and 2;
        hairStyle = gen.nextInt(3);

        //randomize radioChecked selection
        double rand = Math.random();
        if (rand < 0.33) {
            radioChecked = hairRadio;
        }
        else if (rand < 0.66) {
            radioChecked = eyesRadio;
        }
        else if (rand < 1) {
            radioChecked = skinRadio;
        }
    }

    //Helper method to generate a random color
    public int randomColor() {
        return Color.argb(255, (int) (Math.random() * 256),
                (int) (Math.random() * 256),
                (int) (Math.random() * 256));
    }

    //update paints to correspond with bars, draw the face
    public void onDraw(Canvas canvas) {
        canvasWidthCenter = getWidth() / 2;
        canvasHeightCenter = getHeight() / 2;
        skinPaint.setColor(skinColor);
        eyePaint.setColor(eyeColor);
        hairPaint.setColor(hairColor);

        drawFace(canvas);
        drawEyes(canvas);


        if (hairStyle == 0) {
            hairStyle0(canvas);
        } else if (hairStyle == 1) {
            hairStyle1(canvas);
        } else if (hairStyle == 2) {
            hairStyle2(canvas);
        }

    }

    ///////////////////////////////////////////////////////////
    //
    // potential onDraw helpers
    //
    ///////////////////////////////////////////////////////////

    //sets color from seekbars
    public int colorSetter(FaceController faceController, String color, int val) {
        Color skin = new Color();
        return 0;
    }

    //draws general face
    public void drawFace(Canvas canvas) {
        //draw general face shape
        canvas.drawOval((float) (canvasWidthCenter - 200 * faceScaler),
                (float) (canvasHeightCenter - 250 * faceScaler),
                (float) (canvasWidthCenter + 200 * faceScaler),
                (float) (canvasHeightCenter + 250 * faceScaler), skinPaint);

        //draw simple mouth
        canvas.drawArc((float) (canvasWidthCenter - 100 * faceScaler),
                (float) (canvasHeightCenter + 85 * faceScaler),
                (float) (canvasWidthCenter + 100 * faceScaler),
                (float) (canvasHeightCenter + 185 * faceScaler),
                0, 180, false, whitePaint);

    }

    //draws eyes
    public void drawEyes(Canvas canvas) {
        canvas.drawOval((float) (canvasWidthCenter - 100 * faceScaler),
                (float) (canvasHeightCenter - 110 * faceScaler),
                (float) (canvasWidthCenter - 50 * faceScaler),
                (float) (canvasHeightCenter + 15 * faceScaler), eyePaint);

        canvas.drawOval((float) (canvasWidthCenter + 50 * faceScaler),
                (float) (canvasHeightCenter - 110 * faceScaler),
                (float) (canvasWidthCenter + 100 * faceScaler),
                (float) (canvasHeightCenter + 15 * faceScaler), eyePaint);
    }

    //draws hairstyle 0, aka afro
    public void hairStyle0(Canvas canvas) {

        //couple for loops to draw hair, tried to make it look somewhat random but  also natural
        for (int i = 0; i < 8; i++) {
            int j = i * 30;
            if (i % 2 == 0) {
                j = j * -1;
            }

            canvas.drawCircle((float) (canvasWidthCenter + j * faceScaler),
                    (float) (canvasHeightCenter - ((200 - (Math.abs(j) / 2)) * faceScaler)),
                    (float) (70 * faceScaler), hairPaint);
        }
        for (int i = 0; i < 300; i += 100) {
            canvas.drawCircle((float) (canvasWidthCenter - ((100 - i) * faceScaler)),
                    (float) (canvasHeightCenter - (200 * faceScaler)),
                    (float) (70 * faceScaler), hairPaint);
        }
        canvas.drawCircle(canvasWidthCenter,
                (float) (canvasHeightCenter - (150 * faceScaler)),
                (float) (70 * faceScaler), hairPaint);

    }

    //draws hairstyle 1, aka bald
    public void hairStyle1(Canvas canvas) {
        //lazy? yes, but effective, and not against the criteria
    }


    //draws hairstyle 2, aka long hair
    public void hairStyle2(Canvas canvas) {
        //draw hair top
        canvas.drawArc((float)(canvasWidthCenter - 200 * faceScaler),
                (float) (canvasHeightCenter - 275 * faceScaler),
                (float) (canvasWidthCenter + 200 * faceScaler),
                (float) (canvasHeightCenter),
                180, 180, false, hairPaint);

        //draw side and reflect
        for(int i = 0; i < 2; i++) {
            int j = 1;
            if (i == 1) {
                j = -1;
            }

            canvas.drawRect((float)(canvasWidthCenter - 200 * j * faceScaler),
                    (float) (canvasHeightCenter - 140 * faceScaler),
                    (float) (canvasWidthCenter - 130 * j * faceScaler),
                    (float) (canvasHeightCenter + 300 * faceScaler), hairPaint);
        }


    }

}