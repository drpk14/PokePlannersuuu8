package ramos.david.pokeplanner.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

import ramos.david.pokeplanner.API.Pokemon;
import ramos.david.pokeplanner.API.PokemonViewModel;
import ramos.david.pokeplanner.R;

public class PokemonViewer extends AppCompatActivity {
    private PokemonViewModel pokemonViewModel;
    private ImageView spriteImageView;
    private TextView idTextView;
    private TextView nameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_viewer);
        pokemonViewModel = new PokemonViewModel(this.getApplication());
        int idPokemon = getIntent().getExtras().getInt("idPokemon");
        MutableLiveData<List<Pokemon>> pokemonMutableLiveData=
                (MutableLiveData<List<Pokemon>>) pokemonViewModel.getPokemons();

        spriteImageView = findViewById(R.id.spriteShowImageView);
        idTextView = findViewById(R.id.idShowTextField);
        nameTextView = findViewById(R.id.nameShowTextField);


        pokemonMutableLiveData.observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                Iterator it = pokemons.iterator();
                while(it.hasNext()){
                    Pokemon pokemon = (Pokemon) it.next();
                    if(pokemon.getId() == idPokemon){
                        idTextView.setText(pokemon.getId()+"");
                        nameTextView.setText(pokemon.getName());
                        Picasso.get().load(pokemon.getSprite()).resize(200, 200).into(spriteImageView);

                    }
                }
            }
        });
    }
}