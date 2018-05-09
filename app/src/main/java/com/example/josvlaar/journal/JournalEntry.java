package com.example.josvlaar.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    private int id;
    private String title;
    private String content;
    private float mood;
    private String timestamp;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMood(float mood) {
        this.mood = mood;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public float getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
