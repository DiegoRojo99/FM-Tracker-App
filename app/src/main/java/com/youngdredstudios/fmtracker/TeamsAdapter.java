package com.youngdredstudios.fmtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.tv_team_name);
        }

        public TextView getTextView() {
            return textView;
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
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return allTeams.size();
    }
}