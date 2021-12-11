package ramos.david.pokeplanner.RoomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ramos.david.pokeplanner.RoomDataBase.Team;

@Dao
public interface TeamDao {

    @Insert
    void insert(Team team);

    @Query("DELETE FROM team_table")
    void deleteAll();

    @Query("SELECT * from team_table ORDER BY id ASC")
    LiveData<List<Team>> getAllWords();

    @Delete
    void deleteTeam(Team team);

    @Update
    void updateTeam(Team... team);

    @Query("SELECT * FROM team_table WHERE id = :idTeam")
    Team searchTeam(int idTeam);
}
