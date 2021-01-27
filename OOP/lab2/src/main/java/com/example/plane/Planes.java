package com.example.plane;

import java.util.Collections;
import java.util.List;

public class Planes {

    private List<Plane> planes;

    public Planes(List<Plane> planes) {
        this.planes = planes;
    }

    public void sort() {
        Collections.sort(planes);
    }

    @Override
    public String toString() {
        String string = "";
        for (Plane pl : planes) {
            string += pl;
        }
        return string;
    }

    public List<Plane> getList() {
        return planes;
    }
}
