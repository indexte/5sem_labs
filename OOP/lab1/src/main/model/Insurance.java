package main.model;

import java.util.*;

public abstract class Insurance implements Comparable<Insurance>{
    private int cost;
    private int term;
    private int risk;

    public Insurance(int cost, int term, int risk) {
        this.cost = cost;
        this.term = term;
        this.risk = risk;
    }

    public int getCost() {
        return cost;
    }

    public int getTerm() {
        return term;
    }

    public int getRisk() {
        return risk;
    }

    @Override
    public int compareTo(Insurance insurance) {
        return this.risk - insurance.risk;
    }
}
