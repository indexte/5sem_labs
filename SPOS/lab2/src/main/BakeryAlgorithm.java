package main;

import java.util.ArrayList;
import java.util.List;

public class BakeryAlgorithm {

    private static final int NUMBER_OF_THREADS = 11;
    private static  int counter = 0;
    public static final int countToThis = 1;

//    private static ImprovedBakeryLock bakeryLock = new ImprovedBakeryLock(NUMBER_OF_THREADS);
    private static BakeryLock bakeryLock = new BakeryLock(NUMBER_OF_THREADS);

    private static List<Integer> listOfNumbers;


    public static List<Integer> createListOfSumZero(int maxNumberThreads) {
        List<Integer> localNumbList = new ArrayList<>();

        int negativeNumber = maxNumberThreads / 2;
        int positiveNumber = maxNumberThreads / 2;

        if (maxNumberThreads % 2 == 1) {
            localNumbList.add(2);
            positiveNumber -= 1;
            negativeNumber += 1;
        }

        for (int i = 0; i < positiveNumber; i++) {
            localNumbList.add(1);
        }
        for (int i = 0; i <negativeNumber; i++) {
            localNumbList.add(-1);
        }

        return localNumbList;
    }

    private static Runnable function(int value) {
        return () -> {
            for (int i = 0; i < countToThis; i++) {
                bakeryLock.lock();

                counter += value;
                System.out.println("thread " + bakeryLock.getId() + " with counter " + counter);

                bakeryLock.unlock();
            }
        };
    }

    public static void simulateBakeryLock() throws InterruptedException {

        System.out.println("simulation started");

        int[] parameters = new int[NUMBER_OF_THREADS];
        Thread[] threads = new Thread[NUMBER_OF_THREADS];
        listOfNumbers = createListOfSumZero(NUMBER_OF_THREADS);

        System.out.println("list of numbers: " + listOfNumbers);

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            parameters[i] = listOfNumbers.get(i);
        }

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(function(parameters[i]));
            bakeryLock.register(threads[i]);
        }

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i].start();
        }

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {

            threads[i].join();
            bakeryLock.unregister(threads[i]);
        }

        System.out.println("the end");
    }
}
