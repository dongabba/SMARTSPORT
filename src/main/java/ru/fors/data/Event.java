package ru.fors.data;

/**
 * Created by Alexander Zhaleyko on 12.05.2016.
 */
public class Event {
    private String name;
    private String type;
    private String date;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
