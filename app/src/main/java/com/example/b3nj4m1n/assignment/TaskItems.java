package com.example.b3nj4m1n.assignment;

public class TaskItems {
    private String task;
    private String description;

    public TaskItems(String task, String description) {
        this.task = task;
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
