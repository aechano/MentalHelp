package com.example.mentalhelp.Database.Objects;

public class Music {
    private Integer id;
    private int music;
    private String title;

    public Music(Integer id, int music, String title) {
        this.id = id;
        this.music = music;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
