package com.jsphdev.exception;

/**
 * Created by NamitaRane on 11/13/2015.
 */
public class InvalidInputException extends Exception{

    public InvalidInputException(){
        super();
    }

    /**
     * Returns the error message.
     */
    public String getMessage() {
        return "Invalid Input";
    }

}
