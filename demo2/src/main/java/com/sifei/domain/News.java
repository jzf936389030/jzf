package com.sifei.domain;

public class News {

    private String news;
    private String title;
    private Integer id;
    private Integer UID;

    @Override
    public String toString() {
        return "News{" +
                "news='" + news + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", UID=" + UID +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}
