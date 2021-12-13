package ramos.david.pokeplanner.RoomDataBase;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ramos.david.pokeplanner.RoomDataBase.Team;
import ramos.david.pokeplanner.RoomDataBase.TeamRepository;
//
public class TeamViewModel extends AndroidViewModel {

    private TeamRepository mRepository;

    private LiveData<List<Team>> mAllWords;

    public TeamViewModel (Application application) {
        super(application);
        mRepository = new TeamRepository(application);
        mAllWords = mRepository.getAllTeams();
    }

    public LiveData<List<Team>> getAllTeams() { return mAllWords; }

    public void insert(Team team) { mRepository.insert(team);}
    public void delete(Team team){mRepository.delete(team);}
    public void update(Team team){mRepository.update(team);}
    public Team searchTeam(int id){return mRepository.searchTeam(id);}
}
