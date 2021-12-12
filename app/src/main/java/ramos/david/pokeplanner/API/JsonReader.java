package ramos.david.pokeplanner.API;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonReader {


    public static List<Pokemon> obtenerPokemons(String pokemonJSON) {
        if (TextUtils.isEmpty(pokemonJSON)) {
            return null;
        }

        List<Pokemon> pokemons = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(pokemonJSON);

            JSONArray pokemonsArray = baseJsonResponse.getJSONArray("pokemons");

            for (int i = 0; i < pokemonsArray.length() ; i++) {
                JSONObject currentPokemon = pokemonsArray.getJSONObject(i);
                int id = currentPokemon.getInt("id");
                String name = currentPokemon.getString("name");
                String sprite = currentPokemon.getString("sprite");

                pokemons.add(new Pokemon(id,name,sprite));

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("JSonReader", "Problem parsing the urls JSON results", e);
        }

        // Return the list of pokemons
        
        return pokemons;
    }

}
