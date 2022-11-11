package com.example.testproject.Model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModel
{
    @SerializedName("articles")
 public  ArrayList<Data>articles = new ArrayList<>();
    public static class Data{
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;
        @SerializedName("publishedAt")
        private String publishedAt;
        @SerializedName("urlToImage")
        private String urlToImage;

        public Data(String title, String description, String publishedAt, String urlToImage) {
            this.title = title;
            this.description = description;
            this.publishedAt = publishedAt;
            this.urlToImage = urlToImage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }
    }
}
