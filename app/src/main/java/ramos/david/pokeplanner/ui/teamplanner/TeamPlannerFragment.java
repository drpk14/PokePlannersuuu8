package ramos.david.pokeplanner.ui.teamplanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ramos.david.pokeplanner.API.PokemonViewModel;
import ramos.david.pokeplanner.RoomDataBase.Team;
import ramos.david.pokeplanner.RoomDataBase.TeamViewModel;
import ramos.david.pokeplanner.databinding.FragmentTeamplannerBinding;


public class TeamPlannerFragment extends Fragment {

    private FragmentTeamplannerBinding binding;
    private Context context;
    private TeamViewModel teamViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        teamViewModel = new TeamViewModel(this.getActivity().getApplication());

        binding = FragmentTeamplannerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//


        RecyclerView recyclerView = binding.teamRecyclerView;
        context = this.getContext();
        final TeamAdapter adapter = new TeamAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        teamViewModel.getAllTeams().observe(this.getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable final List<Team> teams) {
                // Update the cached copy of the words in the adapter.
                adapter.setTeams(teams);
            }
        });

        binding.addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTeam.class);
                startActivity(intent);
            }
        });



        ItemTouchHelper helperleft = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getLayoutPosition();
                Team team = adapter.getTeamAtPosition(position);
                teamViewModel.delete(team);
            }
        });

        ItemTouchHelper helperright = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getLayoutPosition();
                Team team = adapter.getTeamAtPosition(position);
                Intent intent = new Intent(context,UpdateTeam.class);
                intent.putExtra("idTeam",team.getId());
                startActivity(intent);
            }
        });

        helperleft.attachToRecyclerView(recyclerView);
        helperright.attachToRecyclerView(recyclerView);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}