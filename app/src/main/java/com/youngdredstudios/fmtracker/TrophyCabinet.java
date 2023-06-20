package com.youngdredstudios.fmtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.DateTime;
import com.youngdredstudios.fmtracker.models.Team;
import com.youngdredstudios.fmtracker.models.Trophy;

import java.util.ArrayList;

public class TrophyCabinet extends AppCompatActivity {

    RecyclerView rvTrophyCabinet;
    TrophyAdapter trophyAdapter;

    public void fetchTrophies() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Trophy")
                .whereEqualTo("UserId", "LtRWo4KA5TPR5AwVWle2SEQ2AGu1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Trophy> userTrophies = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String userId= String.valueOf(document.get("UserId"));
                                String teamId= String.valueOf(document.get("TeamId"));
                                String competitionId= String.valueOf(document.get("CompetitionId"));
                                //DateTime wt= (DateTime) document.get("WinningTime");
                                //DateTime gd= (DateTime) document.get("GameDay");
                                Trophy t = new Trophy(teamId,competitionId,userId,null,null);
                                userTrophies.add(t);
                            }
                            trophyAdapter.setUserTrophies(userTrophies); // Notify the adapter about the data changes

                            // Set the adapter on the RecyclerView after data retrieval
                            rvTrophyCabinet.setAdapter(trophyAdapter);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_cabinet);

        rvTrophyCabinet= findViewById(R.id.rv_trophy_cabinet);
        trophyAdapter = new TrophyAdapter(new ArrayList<>());
        rvTrophyCabinet.setAdapter(trophyAdapter);

        // Set the layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTrophyCabinet.setLayoutManager(layoutManager);

        fetchTrophies();
    }
}