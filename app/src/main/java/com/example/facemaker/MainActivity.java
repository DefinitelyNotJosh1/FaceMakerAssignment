//////////////////////////////////
// @author Joshua Krasnogorov
// @version 2/8/2024
//////////////////////////////////

package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //not much reason for this, used for debugging
        Face face = new Face();

        //Create face controller
        FaceController faceController = new FaceController(face,this);


        //retrieve references to seekbars
        SeekBar seekBarRed = findViewById(R.id.seekBarRed);
        SeekBar seekBarGreen = findViewById(R.id.seekBarGreen);
        SeekBar seekBarBlue = findViewById(R.id.seekBarBlue);

        //Register controller to seekbars
        seekBarRed.setOnSeekBarChangeListener(faceController);
        seekBarGreen.setOnSeekBarChangeListener(faceController);
        seekBarBlue.setOnSeekBarChangeListener(faceController);

        //retrieve reference to radio group
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        //Register controller to radio group
        radioGroup.setOnCheckedChangeListener(faceController);












    }
} 