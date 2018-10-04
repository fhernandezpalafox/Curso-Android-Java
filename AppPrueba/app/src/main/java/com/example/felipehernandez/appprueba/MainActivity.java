package com.example.felipehernandez.appprueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnClick;

    private int count =  1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       btnClick  =  findViewById(R.id.btnClick);


       btnClick.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               btnClick.setText(String.format(" %s clicks!",(count++)));
           }
       });
    }


}
