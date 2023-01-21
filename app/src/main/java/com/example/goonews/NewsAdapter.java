package com.example.goonews;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goonews.dto.NewsArticle;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final List<NewsArticle> newsArticles;

    public NewsAdapter(List<NewsArticle> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_row, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder newsViewHolder, int position) {
        NewsArticle newsArticle = newsArticles.get(position);
        newsViewHolder.title.setText(newsArticle.getTitle());
        newsViewHolder.description.setText(newsArticle.getDescription());
        newsViewHolder.url = newsArticle.getUrl();
        Picasso.get().load(newsArticle.getUrlToImage()).into(newsViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
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
                // explicit intent
                Intent intent = new Intent(itemView.getContext(), WebpageActivity.class);
                intent.putExtra("url", url);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
