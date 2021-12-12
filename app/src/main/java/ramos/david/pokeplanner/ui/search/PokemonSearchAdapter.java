package ramos.david.pokeplanner.ui.search;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ramos.david.pokeplanner.API.Pokemon;
import ramos.david.pokeplanner.R;


public class PokemonSearchAdapter extends RecyclerView.Adapter<PokemonSearchAdapter.PokemonViewHolder>{
    private List<Pokemon> pokemons;
    private final LayoutInflater mInflater;
    private Context context;

    public PokemonSearchAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        pokemons = new ArrayList();
    }

    @NonNull
    @Override
    public PokemonSearchAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.pokemon_row, parent, false);
        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonSearchAdapter.PokemonViewHolder holder, int position) {
        Log.d("sadasd","entra");
        if (pokemons != null) {
            Pokemon current = pokemons.get(position);
            holder.nameTextView.setText(current.getName());
            holder.idTextView.setText(current.getId()+"");
            Picasso.get().load(current.getSprite()).resize(200, 200).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    void setPokemons(List<Pokemon> pokemons){
        this.pokemons = pokemons;
        notifyDataSetChanged();
    }

    public Pokemon getPokemonAtPosition (int position) {
        return pokemons.get(position);
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView idTextView;
        private TextView nameTextView;
        private ImageView imageView;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idListTextField);
            nameTextView = itemView.findViewById(R.id.nameListTextField);
            imageView = itemView.findViewById(R.id.spriteListImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getLayoutPosition();
            Pokemon pokemon = getPokemonAtPosition(position);
            Intent intent = new Intent(context, PokemonViewer.class);
            intent.putExtra("idPokemon",pokemon.getId());
            context.startActivity(intent);
        }
    }
}
