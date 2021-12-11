package ramos.david.pokeplanner.API;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonReader {


    public static List<String> obtenerUrls(String pokemonJSON) {
        if (TextUtils.isEmpty(pokemonJSON)) {
            return null;
        }

        List<String> urls = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(pokemonJSON);

            JSONArray pokemonsArray = baseJsonResponse.getJSONArray("results");

            for (int i = 0; i < pokemonsArray.length() ; i++) {
                JSONObject currentpokemonURL = pokemonsArray.getJSONObject(i);
                String url = currentpokemonURL.getString("url");
                urls.add(url);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("JSonReader", "Problem parsing the urls JSON results", e);
        }

        // Return the list of pokemons
        
        return urls;
    }

    public static Pokemon obtenerPokemon(String urlPokemon){
        if (TextUtils.isEmpty(urlPokemon)) {
            return null;
        }
        Pokemon pokemon = null;
        try {

            JSONObject curentPokemon = new JSONObject(urlPokemon);
            int id = curentPokemon.getInt("id");
            String name = curentPokemon.getString("name");
            JSONObject arraySprites = curentPokemon.getJSONObject("sprites");
            String sprite = arraySprites.getString("front_default");
            pokemon = new Pokemon(id,name,sprite);

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("JSonReader", "Problem parsing the pokemon JSON results", e);
        }

        return pokemon;
    }

}
