package app.catslibrary.utility.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import app.catslibrary.R;
import app.catslibrary.utility.datastorage.DB;

public class FavouritesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.favourite_fragment, container, false);
        return root;
    }


    public void onViewCreated(View v, Bundle savedInstnceState){
        //sets up the recycler view for the favourites fragment
        RecyclerView recyclerView = getView().findViewById(R.id.id_rv_favourites);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //sets up the adapter that will have all the favourited cats
        HashMap<Integer, String> map = DB.getFavourites();
        FavouritesAdapter favouritesAdapter = new FavouritesAdapter(map);
        recyclerView.setAdapter(favouritesAdapter);
    }

}