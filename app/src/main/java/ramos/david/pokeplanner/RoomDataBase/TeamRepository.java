package ramos.david.pokeplanner.RoomDataBase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TeamRepository {

    private TeamDao mTeamDao;
    private LiveData<List<Team>> mAllWords;

    public TeamRepository(Application application) {
        TeamRoomDatabase db = TeamRoomDatabase.getDatabase(application);
        mTeamDao = db.wordDao();
        mAllWords = mTeamDao.getAllWords();
    }

    public LiveData<List<Team>> getAllTeams() {
        return mAllWords;
    }

    public void insert (Team team) {new insertAsyncTask(mTeamDao).execute(team);}
    public void delete(Team team){new deleteAsyncTask(mTeamDao).execute(team);}
    public void update(Team team){new updateAsyncTask(mTeamDao).execute(team);}
    public Team searchTeam(Integer id){
        searchTeamAsyncTask SearchAsyncTask = new searchTeamAsyncTask(mTeamDao);
        Team team = null;
        try {
            team = SearchAsyncTask.execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return team;

    }

    private static class insertAsyncTask extends AsyncTask<Team, Void, Void> {

        private TeamDao mAsyncTaskDao;

        insertAsyncTask(TeamDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Team... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class searchTeamAsyncTask extends AsyncTask<Integer, Void, Team> {

        private TeamDao mAsyncTaskDao;

        searchTeamAsyncTask(TeamDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Team doInBackground(Integer... integers) {

            return mAsyncTaskDao.searchTeam(integers[0]);
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Team, Void, Void> {

        private TeamDao mAsyncTaskDao;

        deleteAsyncTask(TeamDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Team... params) {
            mAsyncTaskDao.deleteTeam(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Team, Void, Void> {

        private TeamDao mAsyncTaskDao;

        updateAsyncTask(TeamDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Team... params) {
            mAsyncTaskDao.updateTeam(params[0]);
            return null;
        }
    }
}
