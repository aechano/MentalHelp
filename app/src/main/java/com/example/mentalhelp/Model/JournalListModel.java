package com.example.mentalhelp.Model;

public class JournalListModel {
    Long id;
    String title, content;
    Long dateCreated, dateModified;

    public JournalListModel(String title, Long dateCreated, String content) {
        this.title = title;
        this.dateCreated = dateCreated;
        this.content = content;
    }

    public JournalListModel(Long id, String title, String content, Long dateCreated, Long dateModified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getDateModified() {
        return dateModified;
    }

    public void setDateModified(Long dateModified) {
        this.dateModified = dateModified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
