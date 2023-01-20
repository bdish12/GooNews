package com.example.goonews;

import android.widget.ImageView;

public class NewsData {

    private final String title;
    private final String description;
    private final String url;
    private final String urlToImage;
    private final ImageView imageView;

    public NewsData(String title,
                    String description,
                    String url,
                    String urlToImage,
                    ImageView imageView) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
