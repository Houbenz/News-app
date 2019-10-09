package com.example.newsapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.MyViewHolder> {

    private List<Article> articleList;

    public AdapterNews(List<Article> articles){
        articleList=articles;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public View article_view;
        public MyViewHolder(@NonNull View article_view) {
            super(article_view);
            this.article_view=article_view;
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View product_view =LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view,parent,false);

        return new MyViewHolder(product_view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView publishedAt,title,content,descritpion,author;
        ImageView image;

        publishedAt=holder.article_view.findViewById(R.id.publishedAt);
        title=holder.article_view.findViewById(R.id.title);
        descritpion=holder.article_view.findViewById(R.id.descritpion);
        image=holder.article_view.findViewById(R.id.image);

        String date =articleList.get(position).getPublishedAt().substring(0,10);
        publishedAt.setText("published at: "+date);
        title.setText(articleList.get(position).getTitle());
        descritpion.setText(articleList.get(position).getDescription());
        Picasso.get().load(articleList.get(position).getUrlToImage()).into(image);

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
