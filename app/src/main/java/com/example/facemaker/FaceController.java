package com.example.facemaker;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.SurfaceView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class FaceController implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {
    private Face face;
    private MainActivity mainActivity;
    private int redVal;
    private int greenVal;
    private int blueVal;
    private int buttonID;
    private SurfaceView sv;
    private int hairRadio = 2131296612;
    private int eyesRadio = 2131296611;
    private int skinRadio = 2131296613;

    public FaceController(Face initFace, MainActivity initMainActivity) {
        face = initFace;
        mainActivity = initMainActivity;
        sv = mainActivity.findViewById(R.id.surfaceView);
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        //set correct progress value
        if (seekBar.equals(mainActivity.findViewById(R.id.seekBarRed))) {
            redVal = progress;
        }
        else if (seekBar.equals(mainActivity.findViewById(R.id.seekBarBlue))) {
            blueVal = progress;
        }
        else if (seekBar.equals(mainActivity.findViewById(R.id.seekBarGreen))) {
            greenVal = progress;
        }

        int intColor = Color.argb(255,redVal,greenVal,blueVal);

        //assign color to property identified by radio buttons
        if (buttonID == hairRadio) {
            face.setHairColor(intColor);
        }
        else if (buttonID == eyesRadio) {
            face.setEyeColor(intColor);
        }
        else if (buttonID == skinRadio) {
            face.setSkinColor(intColor);
        }
        else {
            sv.setBackgroundColor(intColor);
        }


        sv.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //does nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //does nothing
    }

    //listen for radio buttons
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //set button ID for seekbar listener
        buttonID = checkedId;

        //set the seekbar values to correspond with the radio buttons
        if (checkedId == hairRadio) {
            seekBarSetter(face.getHairColor());
        }
        else if (checkedId == eyesRadio) {
            seekBarSetter(face.getEyeColor());
        }
        else if (checkedId == skinRadio) {
            seekBarSetter(face.getSkinColor());
        }


        sv.invalidate();
    }

    //seekBar setter helper
    public void seekBarSetter(int color) {
        //set red
        SeekBar seekRed = mainActivity.findViewById(R.id.seekBarRed);
        seekRed.setProgress(Color.red(color));

        //set green
        SeekBar seekGreen = mainActivity.findViewById(R.id.seekBarGreen);
        seekGreen.setProgress(Color.green(color));

        //set blue
        SeekBar seekBlue = mainActivity.findViewById(R.id.seekBarBlue);
        seekBlue.setProgress(Color.blue(color));
    }

}
