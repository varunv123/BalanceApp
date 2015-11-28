package com.jsphdev.entities.model;

import java.io.Serializable;

/**
 * Created by vikramn on 11/13/15.
 */
public class Location implements Serializable{

    private String name;
    private double xCoordinate;
    private double yCoordinate;

    public Location(){

    }

    public Location(double xCoordinate, double yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getxCoordinate() {
        return xCoordinate;
    }
    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public double getyCoordinate() {
        return yCoordinate;
    }
    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public String toString() {
        return "Location[name="+this.name+",xcoordinate="+this.xCoordinate+",ycoordinate="
                +this.yCoordinate+"]";
    }

}
