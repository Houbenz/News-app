package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {



    private Button getNews;
    private TextView news;
    private RequestQueue requestQueue;
    private Gson gson;
    private RecyclerView recyclerNews;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterNews adapterNews;
    private ProgressBar progressBar;

    private Button pagerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNews=findViewById(R.id.getNews);
        news=findViewById(R.id.news);

        GsonBuilder gsonBuilder =new GsonBuilder();
        gson=gsonBuilder.create();

        String url ="https://newsapi.org/v2/top-headlines?country=us&apiKey=63a7ebe91e2e414e808de3968a9035fc";

        requestQueue=RequestQueueSingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        progressBar=findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.GONE);

        recyclerNews=findViewById(R.id.recyclerNews);
        layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerNews.setLayoutManager(layoutManager);
        pagerButton=findViewById(R.id.pagerButton);


        getNews.setOnClickListener(view ->{

            progressBar.setVisibility(View.VISIBLE);
            StringRequest stringRequest =new StringRequest(StringRequest.Method.GET,url,response -> {

                try {


                    progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject= new JSONObject(response);

                    JSONArray jsonArray =jsonObject.getJSONArray("articles");

                    Article[] articles=gson.fromJson(jsonArray.toString(),Article[].class);

                    adapterNews = new AdapterNews(Arrays.asList(articles));
                    recyclerNews.setAdapter(adapterNews);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            },error -> {

                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            });


            requestQueue.add(stringRequest);
        });


        pagerButton.setOnClickListener(view ->{
            Intent intent =new Intent(this,TabActivity.class);
            startActivity(intent);
        });

    }
}
