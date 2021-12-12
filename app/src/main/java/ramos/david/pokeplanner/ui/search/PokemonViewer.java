package ramos.david.pokeplanner.ui.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import ramos.david.pokeplanner.API.Pokemon;
import ramos.david.pokeplanner.API.PokemonViewModel;
import ramos.david.pokeplanner.R;

public class PokemonViewer extends AppCompatActivity {
    private PokemonViewModel pokemonViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_viewer);
        pokemonViewModel = new PokemonViewModel(this.getApplication());
        Pokemon pokemon = pokemonViewModel.searchPokemon((Integer) getIntent().getExtras().get("idPokemon"));
        Toast.makeText(this.getApplicationContext(),pokemon.getName(), Toast.LENGTH_SHORT).show();
    }
}