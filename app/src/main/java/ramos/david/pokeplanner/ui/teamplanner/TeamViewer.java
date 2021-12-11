package ramos.david.pokeplanner.ui.teamplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import ramos.david.pokeplanner.R;
import ramos.david.pokeplanner.RoomDataBase.Team;
import ramos.david.pokeplanner.RoomDataBase.TeamRepository;
import ramos.david.pokeplanner.RoomDataBase.TeamViewModel;

public class TeamViewer extends AppCompatActivity {
    private TeamViewModel teamViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_viewer);
        teamViewModel = new TeamViewModel(this.getApplication());
        Team team = teamViewModel.searchTeam((Integer) getIntent().getExtras().get("idTeam"));

    }
}