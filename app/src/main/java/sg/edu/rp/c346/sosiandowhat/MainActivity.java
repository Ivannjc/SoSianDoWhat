package sg.edu.rp.c346.sosiandowhat;

import android.content.Intent;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDry;
    Button btnWet;
    private SeekBar skBar;
    private TextView tvRate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();
        btnDry = (Button) findViewById(R.id.buttonDry);
        btnWet = (Button)findViewById(R.id.buttonWet);
        skBar = (SeekBar) findViewById(R.id.seekBar);

        tvRate.setText("Covered: " + skBar.getProgress() + "/" + skBar.getMax());

        final int fillDefault = 0;
        skBar.setProgress(fillDefault);
        skBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float decimalProgress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                decimalProgress = (float) (progress);
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvRate.setText("Covered: " + (decimalProgress) + "/" + (seekBar.getMax()));
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });



        btnDry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(skBar.getProgress() <= 50){
                    Intent intentDry = new Intent(getBaseContext(),DryActivity.class);
                    intentDry.putExtra("Mood", "Bad");
                    startActivity(intentDry);
                }else {
                    Intent intentDryGood = new Intent(getBaseContext(),DryActivityGood.class);
                    intentDryGood.putExtra("Mood", "Good");
                    startActivity(intentDryGood);
                }


            }
        });

        btnWet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(skBar.getProgress() <= 50 ){
                    Intent intentWet = new Intent(getBaseContext(),WetActivity.class);
                    intentWet.putExtra("Mood", "Bad");
                    startActivity(intentWet);
                }else {
                    Intent intentWetGood = new Intent(getBaseContext(),WetActivity.class);
                    intentWetGood.putExtra("Mood", "Good");
                    startActivity(intentWetGood);
                }

            }
        });

    }
    private void initializeVariables() {
        skBar = (SeekBar)findViewById(R.id.seekBar);
        tvRate = (TextView)findViewById(R.id.textViewRate);
    }
}
