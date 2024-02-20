package com.example.facemaker;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.google.android.material.textview.MaterialTextView;

public class FaceController implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener,
        View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Face face;
    private MainActivity mainActivity;
    private int redVal;
    private int greenVal;
    private int blueVal;
    private int buttonID;
    private final int hairRadio = 2131296612;
    private final int eyesRadio = 2131296611;
    private final int skinRadio = 2131296613;
    private final String afroSpinner = "Afro";
    private final String baldSpinner = "Bald";
    private final String longSpinner = "Long";


    public FaceController(Face initFace, MainActivity initMainActivity) {
        face = initFace;
        mainActivity = initMainActivity;
    }

    //Seekbar override, detects which seekbar was changed and sets the correct value
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


        face.invalidate();
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


        face.invalidate();
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

        face.invalidate();
    }

    //sets all seekbars for all components
    public void fullSeekBarSetter() {
        seekBarSetter(face.getHairColor());
        seekBarSetter(face.getEyeColor());
        seekBarSetter(face.getSkinColor());
    }


    //randomizer button
    @Override
    public void onClick(View v) {
        face.randomize();
        RadioGroup radioGroup = mainActivity.findViewById(R.id.radioGroup);
        radioGroup.check(face.getRadioChecked());
        spinnerSetter();

        face.invalidate();
    }

    //helper for randomize button, sets spinner to the random hairstyle that was selected
    public void spinnerSetter() {
        Spinner hairSpinner = mainActivity.findViewById(R.id.spinnerHairStyle);
        hairSpinner.setSelection(face.getHairStyle());
    }


    //override method for spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //convert view to materialTextView to get text from it
        MaterialTextView hairTextView = (MaterialTextView) view;
        String hairType = hairTextView.getText() + "";

        //find out which hairstyle was selected
        if (hairType.equals(afroSpinner)) {
            face.setHairStyle(0);
        }
        else if (hairType.equals(baldSpinner)) {
            face.setHairStyle(1);
        }
        else if (hairType.equals(longSpinner)) {
            face.setHairStyle(2);
        }

        face.invalidate();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //does nothing
    }
}
