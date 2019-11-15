package app.catslibrary;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Set;

import app.catslibrary.utility.Cat;
import app.catslibrary.utility.ImageURL;
import app.catslibrary.utility.datastorage.DB;

public class DetailedInfoActivity extends AppCompatActivity {

    Cat cat;
    TextView name;
    ImageView image;
    TextView description;
    TextView weight;
    TextView temperament;
    TextView origin;
    TextView lifespan;
    TextView wiki;
    TextView dogFriendliness;

    private static String NA = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_information);

        // since cat is a serialisable, we can get the entirety of the cat in one go
        cat = (Cat) getIntent().getSerializableExtra("0");

        //finds the views by their ids
        name= findViewById(R.id.name_text);
        description = findViewById(R.id.description_text);
        origin = findViewById(R.id.origin_text);
        lifespan = findViewById(R.id.lifespan_text);
        wiki = findViewById(R.id.wikipedia_text);
        dogFriendliness = findViewById(R.id.friendliness_text);
        weight = findViewById(R.id.weight_text);
        temperament = findViewById(R.id.temperament_text);
        image = findViewById(R.id.image_cat);

        //presets N/A text incase data cannot be retrieved
        name.setText("Name: " + NA);
        description.setText("Description: " + NA);
        weight.setText("Weight: N/A");
        temperament.setText("Temperament: " + NA);
        origin.setText("Origin: " + NA);
        lifespan.setText("Life Span: " + NA);
        wiki.setText("Wiki URL: " + NA);
        dogFriendliness.setText("Friendliness with Dogs: " + NA);

        // sets the information for the cat
        if(cat.getName() != null){
            name.setText(cat.getName());
        }

        if(cat.getDescription() != null){
            description.setText(cat.getDescription());
        }

        if(cat.getWeight() != null) {
            String imperial = cat.getWeight().get("imperial");
            String metric = cat.getWeight().get("metric");
            if(imperial == null){
                imperial = "";
            }
            if(metric == null){
                metric = "";
            }
            weight.setText("Imperial Weight: " + imperial + "\nMetric Weight: " + metric);
        }

        if(cat.getTemperament() != null) {
            temperament.setText("Temperament: " + cat.getTemperament());
        }

        if(cat.getOrigin() != null) {
            origin.setText("Origin: " + cat.getOrigin());
        }

        if(cat.getLifeSpan() != null){
            lifespan.setText("Life Span: " + cat.getLifeSpan());
        }

        if(cat.getWikiUrl() != null){
            wiki.setText("Wiki URL: " + cat.getWikiUrl());
        }

        if(cat.getDogFriendliness() != null) {
            dogFriendliness.setText("Friendliness with Dogs: " + cat.getDogFriendliness());
        }

        //calls the API to retrieve the image and place it in the IMAGEVIEW
        getImageLink(cat.getId());
    }

    // on favourite button click
    public void favourite(View v){
        HashMap<Integer, String> faves = DB.getFavourites();
        Set<String> currentFavourites = DB.getCurrentFavourites();
        if(!currentFavourites.contains(cat.getName())){
            currentFavourites.add(cat.getName());
            faves.put(faves.size(), cat.getName());
        }
    }

    // api call to get the link of the image
    private void getImageLink(String id){
        if(id == null){
            return;
        }

        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + id;
        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ImageURL[] urlResponse = new Gson().fromJson(response, ImageURL[].class);
                if(!(urlResponse == null || urlResponse.length == 0)){
                    getImage(urlResponse[0].getUrl());
                }
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


    // The API used to get the actual image itself using an input URL
    private void getImage(String link){
        if(link == null){
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                // sets the imageView with the response
                image.setImageBitmap(response);
            }
        };
        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        ImageRequest stringRequest = new ImageRequest(link, listener, 0,0,null,errorListener);
        queue.add(stringRequest);
    }
}
