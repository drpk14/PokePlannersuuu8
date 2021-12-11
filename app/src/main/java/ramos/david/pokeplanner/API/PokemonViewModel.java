package ramos.david.pokeplanner.API;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class PokemonViewModel extends AndroidViewModel {
    PokemonRepository repository;

    public PokemonViewModel(@NonNull Application application) {
        super(application);
        repository = new PokemonRepository(application);
    }

    public LiveData<List<Pokemon>> getPokemons(){
            return repository.getPokemons();
        }
}
