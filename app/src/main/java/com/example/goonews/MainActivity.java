package com.example.goonews;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.goonews.dto.NewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String url = "http://newsapi.org/v2/top-headlines?country=ru&apiKey=e8fda39a6bc44a55b5abb787a641f25a";

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private final List<NewsData> newsDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // hide title bar
        setContentView(R.layout.activity_main);
        newsDataPrepare();
    }

    private void newsDataPrepare() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    recyclerView = findViewById(R.id.recycler_view);
                    newsAdapter = new NewsAdapter(newsDataList);
                    RecyclerView.LayoutManager manager = new GridLayoutManager(
                            getApplicationContext(), 1);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(newsAdapter);

                    try {
                        JSONArray jsonArray = response.getJSONArray("articles");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            NewsData newsData = new NewsData(
                                    object.getString("title"),
                                    object.getString("description"),
                                    object.getString("url"),
                                    object.getString("urlToImage")
                            );
                            newsDataList.add(newsData);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show()) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }
}