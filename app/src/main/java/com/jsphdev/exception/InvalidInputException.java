package com.jsphdev.exception;

/**
 * Created by NamitaRane on 11/13/2015.
 */
public class InvalidInputException extends Exception{

    private String message;
    public InvalidInputException(String message){
        super();
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
