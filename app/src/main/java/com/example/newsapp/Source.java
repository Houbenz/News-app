package com.example.newsapp;

public class Source {

    private  String id;
    private String source;

    public Source(String id, String source) {
        this.id = id;
        this.source = source;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
