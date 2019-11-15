package app.catslibrary.utility.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import app.catslibrary.R;
import app.catslibrary.utility.Cat;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private HashMap<Integer, Cat> items;
    private OnClickListener listener;

    // the actual interface on clicking the cat item
    public interface OnClickListener{
        void onCatClick(int position);
    }

    //the viewholder that will display the name of the cat as well as hold the listener
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            listener.onCatClick(getAdapterPosition());
        }

        public TextView name;
        public OnClickListener listener;

        public ViewHolder(View v, OnClickListener listener){
            super(v);
            this.name = v.findViewById(R.id.item);
            this.listener = listener;
            v.setOnClickListener(this);
        }
    }

    public SearchAdapter(HashMap<Integer, Cat> items, OnClickListener listener){
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cat_item,parent,false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {
        viewholder.name.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }





}
