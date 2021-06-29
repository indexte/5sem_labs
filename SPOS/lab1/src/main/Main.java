package main;

import main.manager.Manager;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        long time = System.currentTimeMillis();
        manager.run(3, 2000, 4000);
        long elpTime = System.currentTimeMillis() - time;
        System.out.println("Elapsed time: " + elpTime);
    }
}