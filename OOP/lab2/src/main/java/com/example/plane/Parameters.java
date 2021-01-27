package com.example.plane;

public class Parameters {
    private int length;
    private int height;
    private int width;

    public Parameters() {}

    public Parameters(int length, int height, int width) {
        this.length = length;
        this.height = height;
        this.width = width;
    }

    @Override
    public String toString(){
        return  "{length: " + length
                + ", width: " + width
                + ", height: " + height
                +'}';

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Parameters)) {
            return false;
        }

        Parameters parameters = (Parameters) o;
        return length == parameters.getLength()
                && height == parameters.getHeight()
                && width == parameters.getWidth();
    }

    //getters

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    //setters

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
