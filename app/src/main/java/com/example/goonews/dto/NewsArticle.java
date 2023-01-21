package com.example.goonews.dto;

public class NewsArticle {

    private final String title;
    private final String description;
    private final String url;
    private final String urlToImage;

    public NewsArticle(String title,
                       String description,
                       String url,
                       String urlToImage) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
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
}
