package com.pkp.todokeepingapp;

public class ToDo {
    private int id;
    private String title;
    private String description;
    private long started;
    private long update_date;
    private long finished;

    public ToDo() {
    }

    public ToDo(String title, String description, long started, long update_date, long finished) {
        this.setTitle(title);
        this.setDescription(description);
        this.setStarted(started);
        this.setUpdate_date(update_date);
        this.setFinished(finished);
    }

    public ToDo(int id, String title, String description, long started, long update_date, long finished) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
        this.setStarted(started);
        this.setUpdate_date(update_date);
        this.setFinished(finished);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(long update_date) {
        this.update_date = update_date;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
