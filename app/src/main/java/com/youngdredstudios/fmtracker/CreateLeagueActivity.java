package com.youngdredstudios.fmtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateLeagueActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText teamNameEt, countryCodeET, typeET;
    public Button createCompButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_league);

        teamNameEt=findViewById(R.id.teamNameET);
        countryCodeET=findViewById(R.id.CountryCodeET);
        typeET=findViewById(R.id.CompTypeET);
        createCompButton=findViewById(R.id.createCompButton);
        createCompButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.createCompButton){
            createCompetition();
        }
    }

    private void createCompetition() {
        //FirebaseFirestore db = FirebaseFirestore.getInstance();

    }
}