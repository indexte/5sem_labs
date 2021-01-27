package com.example.plane;

import java.util.*;

public class Plane implements Comparable{

    private String id;
    private String model;
    private String origin;
    private String price;
    private Chars chars;
    private Parameters parameters;

    public Plane(){
        chars = new Chars();
        parameters = new Parameters();
    }

    @Override
    public int compareTo(Object o) {
        return id.compareTo(((Plane) o).getId());
    }

    @Override
    public String toString(){
        return "\nPlane{ "
                +"\n\tId: " + id
                + ", \n\tmodel: " + model
                + ", \n\torigin: " + origin
                + ", \n\tprice: " + price
                + ", \n\tchars: " + chars
                + ", \n\tparameters: " + parameters
                +"\n}";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return id.equals(plane.getId())
                && model.equals(plane.getModel())
                && origin.equals(plane.getOrigin())
                && price.equals(plane.getPrice())
                && chars.equals(plane.getChars())
                && parameters.equals(plane.getParameters());
    }

    //getters

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getOrigin(){
        return origin;
    }

    public String getPrice() {
        return price;
    }

    public Chars getChars() {
        return chars;
    }

    public Parameters getParameters() {
        return parameters;
    }

    //setters

    public void setId(String id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}
