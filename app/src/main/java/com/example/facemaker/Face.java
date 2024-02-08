package com.example.facemaker;

import static java.lang.Integer.parseInt;

import android.graphics.Canvas;

import java.util.Random;

public class Face {

    //instance variables
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    //Face constructor, automatically calls randomize method
    public Face() {
        randomize();
    }

    //Randomizes instance variables
    public void randomize() {
        skinColor = randomColor();
        eyeColor = randomColor();
        hairColor = randomColor();
        hairStyle = randomColor();
    }

    //Helper method to get a random int that contains 3 0-255 values
    public int randomColor() {
        Random gen = new Random();
        String s = "";
        for (int i = 0; i < 3; i++) {
            int j = gen.nextInt(256);
            if (j < 10) {
                s = s + "00" + j;
            }
            else if (j < 100) {
                s = s + "0" + j;
            }
            else {
                s = s + j;
            }
        }

        return Integer.parseInt(s);


    }

    //empty for now
    public void onDraw(Canvas canvas) {

    }

    //
    //
    //
    // potential onDraw helpers
    //
    //
    //

    //helper for color converters - re-adds 0 at beginning if it was lost during int conversion
    public String addZero(int num) {
        String s = "" + num;
        if (num < 100000000) {
            s = "0" + s;
        }
        return s;
    }

    //Fetches blue value of color int
    public int convertBlue(int numberString) {
        String s = addZero(numberString);
        return parseInt(s.substring(6));
    }

    //Fetches green value of color int
    public int convertGreen(int numberString) {
        String s = addZero(numberString);
        return parseInt(s.substring(3,6));
    }

    //fetches red value of color int
    public int FetchRed(int numberString) {
        String s = addZero(numberString);
        return parseInt(s.substring(0,3));
    }
}
