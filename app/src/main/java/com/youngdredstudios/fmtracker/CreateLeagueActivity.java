package com.youngdredstudios.fmtracker;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> league = new HashMap<>();
        league.put("Name", teamNameEt.getText().toString());
        league.put("Type", typeET.getText().toString());
        league.put("CountryCode", countryCodeET.getText().toString());
        league.put("Trophy", "LogoImage");

        db.collection("Competition").document()
                .set(league)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));

        teamNameEt.getText().clear();
        typeET.getText().clear();
        countryCodeET.getText().clear();

    }
}