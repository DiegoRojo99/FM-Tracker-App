package com.youngdredstudios.fmtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.youngdredstudios.fmtracker.models.Team;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AllTeamsActivity extends AppCompatActivity {

    public RecyclerView rvAllTeams;
    public TeamsAdapter teamsAdapter;

    public void fetchTeams() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Teams")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Team> allTeams = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String cc = document.getData().get("CountryCode").toString();
                                String name = document.getData().get("Name").toString();
                                String badge = document.getData().get("Badge").toString();
                                Team t = new Team(document.getId(), badge, cc, name);
                                allTeams.add(t);
                            }
                            teamsAdapter.setAllTeams(allTeams); // Notify the adapter about the data changes

                            // Set the adapter on the RecyclerView after data retrieval
                            rvAllTeams.setAdapter(teamsAdapter);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_teams);

        rvAllTeams= findViewById(R.id.rv_all_teams);
        teamsAdapter = new TeamsAdapter(new ArrayList<>());
        rvAllTeams.setAdapter(teamsAdapter);

        // Set the layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvAllTeams.setLayoutManager(layoutManager);

        fetchTeams();
    }


}