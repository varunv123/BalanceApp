package com.jsphdev.model;

/**
 * Created by vikramn on 11/13/15.
 */
public class Log {

    private String name;
    private String type;
    private String message;

    public Log(String name,String type, String message){
        this.name = name;
        this.type = type;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
