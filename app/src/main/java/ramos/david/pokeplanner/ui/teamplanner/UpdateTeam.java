package ramos.david.pokeplanner.ui.teamplanner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ramos.david.pokeplanner.R;
import ramos.david.pokeplanner.RoomDataBase.Team;
import ramos.david.pokeplanner.RoomDataBase.TeamViewModel;

public class UpdateTeam extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TeamViewModel teamViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        teamViewModel = new TeamViewModel(this.getApplication());
        Team team = teamViewModel.searchTeam((Integer) getIntent().getExtras().get("idTeam"));

        editText = findViewById(R.id.insertNameText);
        editText.setText(team.getTeamName());
        button = findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.length() != 0){
                    team.setTeamName(editText.getText().toString());
                    teamViewModel.update(team);
                    finish();
                }
            }
        });
    }//
}
