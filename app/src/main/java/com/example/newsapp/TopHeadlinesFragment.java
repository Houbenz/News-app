package com.example.newsapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class TopHeadlinesFragment extends Fragment {

    private RequestQueue requestQueue;
    private AdapterNews adapterNews;
    private Gson gson;
    private ProgressBar progressBar2;
    private String url;
    public TopHeadlinesFragment(String url){
        this.url=url;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        progressBar2=view.findViewById(R.id.progressBar2);
        progressBar2.setIndeterminate(true);
        requestQueue=RequestQueueSingleton.getInstance(this.getContext()).getRequestQueue();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GsonBuilder gsonBuilder =new GsonBuilder();
        gson=gsonBuilder.create();
        RecyclerView recyclerView =view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        StringRequest stringRequest =new StringRequest(StringRequest.Method.GET,url, response -> {

            try {

                progressBar2.setVisibility(View.GONE);
                JSONObject jsonObject= new JSONObject(response);

                JSONArray jsonArray =jsonObject.getJSONArray("articles");

                Article[] articles=gson.fromJson(jsonArray.toString(),Article[].class);

                adapterNews = new AdapterNews(Arrays.asList(articles));
                recyclerView.setAdapter(adapterNews);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        },error -> {

            Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
        });


        requestQueue.add(stringRequest);

    }
}
