package ramos.david.pokeplanner.ui.teamplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ramos.david.pokeplanner.R;
import ramos.david.pokeplanner.RoomDataBase.Team;
import ramos.david.pokeplanner.RoomDataBase.TeamViewModel;
//
public class AddTeam extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private TeamViewModel teamViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        teamViewModel = new TeamViewModel(this.getApplication());
        editText = findViewById(R.id.insertNameText);
        button = findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.length() != 0){
                    teamViewModel.insert(new Team(editText.getText().toString()));
                    finish();
                }
            }
        });

    }
}