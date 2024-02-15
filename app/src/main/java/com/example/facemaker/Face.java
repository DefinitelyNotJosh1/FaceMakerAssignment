//////////////////////////////////
// @author Joshua Krasnogorov
// @version 2/8/2024
//////////////////////////////////

package com.example.facemaker;

import static java.lang.Integer.parseInt;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Face {

    //instance variables
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

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



    //Face constructor, automatically calls randomize method
    public Face() {
        randomize();
    }

    //Randomizes instance variables
    public void randomize() {
        Random gen = new Random();

        skinColor = randomColor();
        eyeColor = randomColor();
        hairColor = randomColor();
        //randomize hairstyle to value between 0 and 2;
        hairStyle = gen.nextInt(3);
    }

    //Helper method to generate a random color
    public int randomColor() {
        return Color.argb( 255, (int) (Math.random() * 256),
                       (int) (Math.random() * 256),
                       (int) (Math.random() * 256));
    }

    //empty for now
    public void onDraw(Canvas canvas) {
        //TODO: empty for now

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
        //TODO: empty for now
    }

    //draws hairstyle 0
    public void hairStyle0(Canvas canvas) {
        //TODO: empty for now
    }

    //draws hairstyle 1
    public void hairStyle1(Canvas canvas) {
        //TODO: empty for now
    }

    //draws hairstyle 2
    public void hairStyle2(Canvas canvas) {
        //TODO: empty for now
    }

    //draws eyes
    public void drawEyes(Canvas canvas) {
        //TODO: empty for now
    }

    //draws nose
    public void drawNose(Canvas canvas) {
        //TODO: empty for now
    }

}
