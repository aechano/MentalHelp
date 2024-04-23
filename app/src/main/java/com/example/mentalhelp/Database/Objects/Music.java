package com.example.mentalhelp.Database.Objects;

public class Music {
    private Integer id;
    private String music;
    private String title;

    public Music(Integer id, String music, String title) {
        this.id = id;
        this.music = music;
        this.title = title;
    }

    public Music() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
