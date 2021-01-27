package main.derivative;

import main.model.*;

public class UserInterface {
    public void run() {
        Derivative derivative = new Derivative("123");

        HomeInsurance homeIns = new HomeInsurance(1500, 1, 5, "house");
        HomeInsurance homeIns2 = new HomeInsurance(3400, 3, 15, "cottage");
        HomeInsurance homeIns3 = new HomeInsurance(2100, 2, 10, "penthouse");
        VehicleInsurance vehIns = new VehicleInsurance(300, 4, 20, "BMW");
        VehicleInsurance vehIns2 = new VehicleInsurance(250, 5, 12, "Honda");

        derivative.addInsurance(homeIns);
        derivative.addInsurance(homeIns2);
        derivative.addInsurance(homeIns3);
        derivative.addInsurance(vehIns);
        derivative.addInsurance(vehIns2);

        System.out.println("Derivative: ");
        derivative.printDerivative();
        System.out.println(System.lineSeparator());

        System.out.println("Total cost: ");
        System.out.println(derivative.calcCost() + System.lineSeparator());

        System.out.println("Sorted derivative: ");
        derivative.reverseSortByRisk();
        derivative.printDerivative();
        System.out.println(System.lineSeparator());

        System.out.println("Find insurance by cost range, from 100 to 1000");
        System.out.println(derivative.findByCost(100, 1000) + System.lineSeparator());

        System.out.println("Find insurance by term range, from 3 to 10");
        System.out.println(derivative.findByTerm(3, 10) + System.lineSeparator());

        System.out.println("Find insurance by risk range, from 10 to 15");
        System.out.println(derivative.findByRisk(10, 15) + System.lineSeparator());
    }
}
