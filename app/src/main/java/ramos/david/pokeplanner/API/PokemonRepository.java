package ramos.david.pokeplanner.API;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PokemonRepository {

    MutableLiveData<List<Pokemon>> pokemons;

    public PokemonRepository(Application application) {
        this.application = application;
    }

    Application application;
    private static final String FISRT_REQUEST_URL = "https://pokeapi.co/api/v2/pokemon/";

    public LiveData<List<Pokemon>> getPokemons(){

        if (pokemons == null){
            pokemons= new MutableLiveData<>();
            cargaUrls();
        }

        return pokemons;
    }

    private void cargaUrls() {

        Uri baseUri = Uri.parse(FISRT_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("limit","151");

        final RequestQueue requestQueue = Volley.newRequestQueue(application);
        StringRequest request = new StringRequest(Request.Method.GET, uriBuilder.build().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       List<String> urls = JsonReader.obtenerUrls(response);
                       cargarPokemon(urls);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("PokemonRepository", "Error en la carga de volley"+ error.getMessage());
                    }
                });
        requestQueue.add(request);
    }

    private void cargarPokemon(List<String> urls){

        Iterator it = urls.iterator();
        List<Pokemon> pokemonsProvisional = new ArrayList();
        while(it.hasNext()) {
            String url = (String) it.next();
            Uri baseUri = Uri.parse(url);
            Uri.Builder uriBuilder = baseUri.buildUpon();
            final RequestQueue requestQueue = Volley.newRequestQueue(application);
            StringRequest request = new StringRequest(Request.Method.GET, uriBuilder.build().toString(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Pokemon pokemon = JsonReader.obtenerPokemon(response);
                            pokemonsProvisional.add(pokemon);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e("PokemonRepository", "Error en la carga de volley"+ error.getMessage());
                        }
                    });
            requestQueue.add(request);
        }
        pokemons.postValue(pokemonsProvisional);
    }
}
