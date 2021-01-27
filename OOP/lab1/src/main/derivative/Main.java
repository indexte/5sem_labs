package main.derivative;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import test.model.DerivativeTest;

public class Main {
    public static void main(String[] args) {

        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(DerivativeTest.class);

        UserInterface ui = new UserInterface();
        ui.run();
    }
}
