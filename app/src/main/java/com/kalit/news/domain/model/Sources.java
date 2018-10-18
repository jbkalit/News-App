package com.kalit.news.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KALIT on 9/16/2018.
 */

public class Sources {
    @SerializedName("sources")
    private List<SourceItem> sources;

    @SerializedName("status")
    private String status;

    public void setSources(List<SourceItem> sources){
        this.sources = sources;
    }

    public List<SourceItem> getSources(){
        return sources;
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
                "NewsSources{" +
                        "sources = '" + sources + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
