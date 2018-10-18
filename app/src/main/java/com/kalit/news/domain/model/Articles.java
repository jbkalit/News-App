package com.kalit.news.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KALIT on 9/16/2018.
 */

public class Articles {
    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<ArticleItem> articles;

    @SerializedName("status")
    private String status;

    public void setTotalResults(int totalResults){
        this.totalResults = totalResults;
    }

    public int getTotalResults(){
        return totalResults;
    }

    public void setArticles(List<ArticleItem> articles){
        this.articles = articles;
    }

    public List<ArticleItem> getArticles(){
        return articles;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "NewsItem{" +
                        "totalResults = '" + totalResults + '\'' +
                        ",articles = '" + articles + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
