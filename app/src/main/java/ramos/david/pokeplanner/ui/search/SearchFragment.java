package ramos.david.pokeplanner.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ramos.david.pokeplanner.API.Pokemon;
import ramos.david.pokeplanner.API.PokemonViewModel;
import ramos.david.pokeplanner.databinding.FragmentSearchBinding;


public class SearchFragment extends Fragment {
//
    private FragmentSearchBinding binding;
    private PokemonViewModel pokemonViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        pokemonViewModel = new PokemonViewModel(this.getActivity().getApplication());

        RecyclerView recyclerView = binding.pokemonRecyclerView;
        final PokemonSearchAdapter adapter = new PokemonSearchAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        pokemonViewModel.getPokemons().observe(this.getViewLifecycleOwner(), new Observer<List<Pokemon>>() {

            @Override
            public void onChanged(@Nullable final List<Pokemon> pokemons) {
                // Update the cached copy of the words in the adapter.
                adapter.setPokemons(pokemons);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}