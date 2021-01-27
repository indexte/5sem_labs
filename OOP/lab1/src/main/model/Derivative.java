package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Derivative {
    private List<Insurance> insurances;
    private String number;
    public Derivative(String number) {
        insurances = new ArrayList<>();
        this.number = number;
    }

    public void addInsurance(Insurance insurance) {
        insurances.add(insurance);
    }

    public void printDerivative() {
        System.out.println(insurances);
    }

    public int calcCost() {
        int totalCost = 0;
        for (int i = 0; i < insurances.size(); i++) {
            totalCost += insurances.get(i).getCost();
        }
        return totalCost;
    }

    //for tests
    public int getSize() {
        return insurances.size();
    }

    public void reverseSortByRisk() {
        Collections.sort(insurances);
        Collections.reverse(insurances);
    }

    //for tests
    public Insurance findFirstElement() {
        reverseSortByRisk();
        return insurances.get(0);
    }

    public List<Insurance> findByCost(int from, int to) {
        List<Insurance> neededInsurances = new ArrayList<>();

        for (Insurance insurance : insurances) {
            if (insurance.getCost() >= from & insurance.getCost() <= to) {
                neededInsurances.add(insurance);
            }
        }
        return neededInsurances;
    }

    public List<Insurance> findByTerm(int from, int to) {
        List<Insurance> neededInsurances = new ArrayList<>();

        for (Insurance insurance : insurances) {
            if (insurance.getTerm() >= from & insurance.getTerm() <= to) {
                neededInsurances.add(insurance);
            }
        }
        return neededInsurances;
    }

    public List<Insurance> findByRisk(int from, int to) {
        List<Insurance> neededInsurances = new ArrayList<>();

        for (Insurance insurance : insurances) {
            if (insurance.getRisk() >= from & insurance.getRisk() <= to) {
                neededInsurances.add(insurance);
            }
        }
        return neededInsurances;
    }
}
