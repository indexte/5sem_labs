package test.model;

import main.model.Derivative;
import main.model.HomeInsurance;
import main.model.Insurance;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DerivativeTest {

    @Test
    public void addInsurance() {
        Derivative d = new Derivative("123");
        HomeInsurance ins = new HomeInsurance(12000, 1, 10, "flat");
        HomeInsurance ins2 = new HomeInsurance(1500, 4, 5, "flat");
        d.addInsurance(ins);
        d.addInsurance(ins2);

        int actual = d.getSize();
        int expected = 2;
        assertEquals(actual, expected);
    }

    @Test
    public void calcCost() {
        Derivative d = new Derivative("123");
        HomeInsurance ins = new HomeInsurance(12000, 1, 10, "flat");
        HomeInsurance ins2 = new HomeInsurance(1500, 4, 5, "flat");
        d.addInsurance(ins);
        d.addInsurance(ins2);

        int actual = d.calcCost();
        int expected = 13500;
        assertEquals(actual, expected);
    }

    @Test
    public void reverseSortByRisk() {
        Derivative d = new Derivative("123");
        HomeInsurance ins = new HomeInsurance(12000, 1, 10, "flat");
        HomeInsurance ins2 = new HomeInsurance(1500, 4, 5, "flat");
        HomeInsurance ins3 = new HomeInsurance(500, 5, 25, "flat");
        d.addInsurance(ins);
        d.addInsurance(ins2);
        d.addInsurance(ins3);
        d.reverseSortByRisk();

        Insurance actual = d.findFirstElement();
        Insurance expected = ins3;
        assertEquals(actual, expected);
    }

    @Test
    public void findByCost() {
        Derivative d = new Derivative("123");
        HomeInsurance ins = new HomeInsurance(12000, 1, 10, "flat");
        HomeInsurance ins2 = new HomeInsurance(1500, 4, 5, "flat");
        HomeInsurance ins3 = new HomeInsurance(500, 5, 25, "flat");
        d.addInsurance(ins);
        d.addInsurance(ins2);
        d.addInsurance(ins3);

        List<Insurance> actual = d.findByCost(500, 3000);
        List<Insurance> expected = new ArrayList<>();
        expected.add(ins2);
        expected.add(ins3);
        assertEquals(actual, expected);
    }

    @Test
    public void findByTerm() {
        Derivative d = new Derivative("123");
        HomeInsurance ins = new HomeInsurance(12000, 1, 10, "flat");
        HomeInsurance ins2 = new HomeInsurance(1500, 4, 5, "flat");
        HomeInsurance ins3 = new HomeInsurance(500, 5, 25, "flat");
        d.addInsurance(ins);
        d.addInsurance(ins2);
        d.addInsurance(ins3);

        List<Insurance> actual = d.findByTerm(2, 5);
        List<Insurance> expected = new ArrayList<>();
        expected.add(ins2);
        expected.add(ins3);
        assertEquals(actual, expected);
    }

    @Test
    public void findByRisk() {
        Derivative d = new Derivative("123");
        HomeInsurance ins = new HomeInsurance(12000, 1, 10, "flat");
        HomeInsurance ins2 = new HomeInsurance(1500, 4, 5, "flat");
        HomeInsurance ins3 = new HomeInsurance(500, 5, 25, "flat");
        d.addInsurance(ins);
        d.addInsurance(ins2);
        d.addInsurance(ins3);

        List<Insurance> actual = d.findByRisk(5, 12);
        List<Insurance> expected = new ArrayList<>();
        expected.add(ins);
        expected.add(ins2);
        assertEquals(actual, expected);
    }
}