package com.customappbar.tomatoclock;

import android.app.Dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TomatoView clockView;
    ImageButton imgbtn;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.features_meun,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clockView =(TomatoView) findViewById(R.id.clockView);
        clockView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final Dialog d = new Dialog(MainActivity.this);//選擇時間對話框（使用NumberPicker
        d.setTitle("選擇時間");
        d.setContentView(R.layout.activity_time_pick);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(5); // max value 4
        np.setMinValue(0);   // min value 0
        final String[] minutes = {"0", "5", "10", "15", "20", "25"};
        np.setDisplayedValues(minutes);
        np.setWrapSelectorWheel(false);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getCurrentValue= minutes[np.getValue()];//抓取的陣列位置轉換成值放進getCurrentValue
                       Toast.makeText(MainActivity.this, "顯示:" + getCurrentValue, Toast.LENGTH_LONG).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();
            }
        });
        imgbtn=(ImageButton)findViewById(R.id.imgBtn);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }    
}
