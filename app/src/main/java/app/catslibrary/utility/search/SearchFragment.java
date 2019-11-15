package app.catslibrary.utility.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;

import app.catslibrary.DetailedInfoActivity;
import app.catslibrary.R;
import app.catslibrary.utility.Cat;

public class SearchFragment extends Fragment implements SearchAdapter.OnClickListener {


    private HashMap<Integer, Cat> map;
    private EditText searchBar;
    private ImageView icon;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.search_fragment, container, false);
        return root;
    }

    public void onViewCreated(View v, Bundle savedInstnceState){
        searchBar = getView().findViewById(R.id.searchbar);
        icon = getView().findViewById(R.id.searchbutton);
        map = new HashMap<Integer, Cat>();

        icon.setOnClickListener(getOnClickListener());
    }

    private View.OnClickListener getOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.clear();
                String input = searchBar.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url = "https://api.thecatapi.com/v1/breeds/search?q=" + input;
                final Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        update(response);
                    }
                };
                final Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                };
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
                queue.add(stringRequest);
            }
        };
    }

    //refreshes the recycler view with the most up to date details everytime a search is done
    private void update(String response){
        Cat[] res = new Gson().fromJson(response, Cat[].class);
        for(int i = 0; i < res.length; i++){
            map.put(i, res[i]);
        }
        RecyclerView recyclerView = getView().findViewById(R.id.searchrecyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        SearchAdapter itemAdapter = new SearchAdapter(map, this);
        recyclerView.setAdapter(itemAdapter);
    }

    // when an item is clicked, move to the DetailedInfoActivity with all the information
    @Override
    public void onCatClick(int position) {
        Cat cat = map.get(position);
        Intent intent = new Intent(getActivity(), DetailedInfoActivity.class);
        intent.putExtra("0", cat);
        startActivity(intent);
    }
}