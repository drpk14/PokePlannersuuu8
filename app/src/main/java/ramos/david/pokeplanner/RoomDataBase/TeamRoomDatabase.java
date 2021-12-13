package ramos.david.pokeplanner.RoomDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//
@Database(entities = {Team.class}, version = 2, exportSchema = false)
public abstract class TeamRoomDatabase extends RoomDatabase {

    public abstract TeamDao wordDao();
    private static TeamRoomDatabase INSTANCE;

    public static TeamRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TeamRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TeamRoomDatabase.class, "team_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
