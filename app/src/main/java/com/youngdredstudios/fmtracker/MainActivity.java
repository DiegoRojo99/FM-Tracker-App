package com.youngdredstudios.fmtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button cabinetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cabinetButton = findViewById(R.id.cabinet_button);
        cabinetButton.setOnClickListener(this);
    }


    public void goToCabinet(){
        Intent cabinetIntent = new Intent(getApplicationContext(), TrophyCabinet.class);
        startActivity(cabinetIntent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.cabinet_button){
            goToCabinet();
        }
    }
}