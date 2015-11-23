package com.jsphdev.exception;

import android.util.Log;

/**
 * CustomReadException - Custom exception to be thrown when invalid input is encountered
 *
 * Created by vikramn on 11/13/15.
 */
public class CustomReadException extends Exception {
    private static final long serialVersionUID = 1L;

    private String message = null;

    public CustomReadException() {
        super();
    }

    public CustomReadException(String message) {
        super(message);
        this.message = message;
        this.logError(message);
    }

    public CustomReadException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void logError(String msg) {
        Log.e("ERROR",msg);
    }
}
