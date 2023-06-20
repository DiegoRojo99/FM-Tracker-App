package com.youngdredstudios.fmtracker;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.DateTime;
import com.youngdredstudios.fmtracker.models.Team;
import com.youngdredstudios.fmtracker.models.Trophy;

import java.util.ArrayList;
import java.util.Date;

public class TrophyAdapter extends RecyclerView.Adapter<TrophyAdapter.ViewHolder> {

    public ArrayList<Trophy> userTrophies;

    public void setUserTrophies(ArrayList<Trophy> userTrophies) {
        this.userTrophies = userTrophies;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTV, counterTV;
        private final ImageView flagIV, trophyIV;

        public ViewHolder(View view) {
            super(view);
            nameTV = view.findViewById(R.id.tv_trophy_name);
            flagIV = view.findViewById(R.id.iv_trophy_flag);
            counterTV = view.findViewById(R.id.tv_trophy_counter);
            trophyIV = view.findViewById(R.id.iv_trophy_logo);
        }

        public TextView getCounterTV() {
            return counterTV;
        }
        public ImageView getFlagIV() {
            return flagIV;
        }
        public TextView getNameTV() {
            return nameTV;
        }
        public ImageView getTrophyIV() {
            return trophyIV;
        }
    }

    public TrophyAdapter(ArrayList<Trophy> trophies) {
        userTrophies=trophies;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trophy_card, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String userId=userTrophies.get(position).getUserId();
        String competitionId=userTrophies.get(position).getCompetitionId();
        String teamId=userTrophies.get(position).getTeamId();
        DateTime gameDay=userTrophies.get(position).getGameDay();
        DateTime winningTime=userTrophies.get(position).getWinningTime();

        Trophy t = new Trophy(teamId, competitionId, userId, winningTime, gameDay);
        updateTrophyInfo(t, viewHolder);

    }
    public void updateTrophyInfo(Trophy t, ViewHolder v){
        getCompetition(t.CompetitionId, v);

    }

    public void getCompetition(String id, ViewHolder v){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Competition")
                .document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        getCountryFlag(String.valueOf(document.get("CountryCode")),v);
                        changeCompName(String.valueOf(document.get("Name")), v);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void changeCompName(String name, ViewHolder v){
        v.getNameTV().setText(name);
    }
    public void changeCountryFlag(String flag, ViewHolder v){
        switch (flag){
            case "poland":
                v.getFlagIV().setImageResource(R.drawable.poland);
                break;
            case "netherlands":
                v.getFlagIV().setImageResource(R.drawable.netherlands);
                break;
            case "spain":
                v.getFlagIV().setImageResource(R.drawable.spain);
                break;
            case "england":
                v.getFlagIV().setImageResource(R.drawable.england);
                break;
            default:
                //Log.d("TAG-DIEGO",badge);
                v.getFlagIV().setImageResource(R.drawable.race);
                break;
        }
    }

    public void getCountryFlag(String cc, ViewHolder v){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Country")
                .whereEqualTo("CountryCode", cc)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String flag= String.valueOf(document.getData().get("Flag"));
                                changeCountryFlag(flag, v);
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }

                });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return userTrophies.size();
    }
}