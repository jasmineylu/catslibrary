package app.catslibrary.utility.favourites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import app.catslibrary.R;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder>{

    private HashMap<Integer, String> items;
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;

        public ViewHolder(View v){
            super(v);
            this.name = v.findViewById(R.id.item);
        }
    }

    public FavouritesAdapter(HashMap<Integer, String> items){
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cat_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {
        viewholder.name.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }





}
