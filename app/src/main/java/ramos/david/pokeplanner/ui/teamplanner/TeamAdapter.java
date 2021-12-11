package ramos.david.pokeplanner.ui.teamplanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ramos.david.pokeplanner.R;
import ramos.david.pokeplanner.RoomDataBase.Team;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder>{
    private List<Team> teams;
    private final LayoutInflater mInflater;
    private Context context;

    public TeamAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        teams = new ArrayList();
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.team_row, parent, false);
        return new TeamViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        if (teams != null) {
            Team current = teams.get(position);
            holder.TeamTextView.setText(current.getTeamName());
        } else {
            // Covers the case of data not being ready yet.
            holder.TeamTextView.setText("No Word");
        }
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    void setTeams(List<Team> teams){
        this.teams = teams;
        notifyDataSetChanged();
    }

    public Team getTeamAtPosition (int position) {
        return teams.get(position);
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView TeamTextView;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            TeamTextView = itemView.findViewById(R.id.teamName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getLayoutPosition();
            Team team = getTeamAtPosition(position);
            Intent intent = new Intent(context,TeamViewer.class);
            Toast.makeText(context, team.getTeamName(), Toast.LENGTH_SHORT).show();
            intent.putExtra("idTeam",team.getId());
            context.startActivity(intent);
        }
    }
}
