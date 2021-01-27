package main.model;

public class VehicleInsurance extends Insurance {

    private String carBrand;

    public VehicleInsurance(int cost, int term, int risk, String carBrand) {
        super(cost, term, risk);
        this.carBrand = carBrand;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String toString() {
        return "Vehicle Insurance(" +
                "cost=" + getCost() +
                ", term=" + getTerm() +
                ", risk=" + getRisk() +
                ", car brand=" + getCarBrand() +
                ')' + System.lineSeparator();
    }
}
