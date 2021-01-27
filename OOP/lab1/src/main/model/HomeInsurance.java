package main.model;

public class HomeInsurance extends Insurance {

    private String homeType;

    public HomeInsurance(int cost, int term, int risk, String homeType) {
        super(cost, term, risk);
        this.homeType = homeType;
    }

    public String getHomeType() {
        return homeType;
    }

    public String toString() {
        return "Home Insurance(" +
                "cost=" + getCost() +
                ", term=" + getTerm() +
                ", risk=" + getRisk() +
                ", home type=" + getHomeType() +
                ')' + System.lineSeparator();
    }
}
