package com.youngdredstudios.fmtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button cabinetButton, leagueButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("TAG_DIEGO","LLEGAMOS 1");

        cabinetButton = findViewById(R.id.cabinet_button);
        cabinetButton.setOnClickListener(this);
        leagueButton = findViewById(R.id.leagueButton);
        leagueButton.setOnClickListener(this);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        Log.d("TAG_DIEGO","LLEGAMOS 2");
    }


    public void goToCabinet(){
        Intent cabinetIntent = new Intent(getApplicationContext(), TrophyCabinet.class);
        startActivity(cabinetIntent);
    }
    public void goToLogin(){
        Intent cabinetIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(cabinetIntent);
    }
    public void goToLeague(){
        Intent cabinetIntent = new Intent(getApplicationContext(), CreateLeagueActivity.class);
        startActivity(cabinetIntent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.cabinet_button){
            goToCabinet();
        } else if (view.getId()==R.id.loginButton) {
            goToLogin();
        }else if(view.getId()==R.id.leagueButton){
            goToLeague();
        }
    }
}