package com.example.goonews;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goonews.dto.NewsData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final List<NewsData> newsDataList;

    public NewsAdapter(List<NewsData> newsDataList) {
        this.newsDataList = newsDataList;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_row, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        NewsData newsData = newsDataList.get(position);
        holder.title.setText(newsData.getTitle());
        holder.description.setText(newsData.getDescription());
        holder.url = newsData.getUrl();
        Picasso.get().load(newsData.getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder  {

        private final TextView title;
        private final TextView description;
        private final ImageView imageView;
        private String url;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), WebpageActivity.class);
                intent.putExtra("url", url);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
