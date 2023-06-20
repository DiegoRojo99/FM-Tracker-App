package com.youngdredstudios.fmtracker;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.youngdredstudios.fmtracker.models.Team;

import java.util.ArrayList;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    public ArrayList<Team> allTeams;

    public void setAllTeams(ArrayList<Team> allTeams) {
        this.allTeams = allTeams;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView flagIV;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.tv_team_name);
            flagIV = view.findViewById(R.id.iv_team_flag);
        }

        public TextView getTextView() {
            return textView;
        }
        public ImageView getFlagIV() {
            return flagIV;
        }
    }

    public TeamsAdapter(ArrayList<Team> teams) {
        allTeams=teams;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.team_card, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(allTeams.get(position).getName());
        getCountryFlag(allTeams.get(position).getCountryCode(), viewHolder);

    }

    public void changeCountryFlag(String flag,ViewHolder viewHolder){
        switch (flag){
            case "poland":
                viewHolder.getFlagIV().setImageResource(R.drawable.poland);
                break;
            case "netherlands":
                viewHolder.getFlagIV().setImageResource(R.drawable.netherlands);
                break;
            case "spain":
                viewHolder.getFlagIV().setImageResource(R.drawable.spain);
                break;
            case "england":
                viewHolder.getFlagIV().setImageResource(R.drawable.england);
                break;
            default:
                //Log.d("TAG-DIEGO",badge);
                viewHolder.getFlagIV().setImageResource(R.drawable.race);
                break;
        }
    }

    public void getCountryFlag(String cc, ViewHolder viewHolder){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Country")
                .whereEqualTo("CountryCode", cc)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String flag=document.getData().get("Flag").toString();
                                changeCountryFlag(flag,viewHolder);
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
        return allTeams.size();
    }
}