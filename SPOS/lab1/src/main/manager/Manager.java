package main.manager;

import main.functions.*;
import main.util.Cancellation;

import java.util.ArrayList;

public class Manager {

    public int resF;
    public int resG;
    public int resFinal;
    public int waitTimeF;
    public int waitTimeG;
    public boolean calcF = true;
    public boolean calcG = true;
    ArrayList<Thread> curThreads = new ArrayList<>();

    Thread threadF = new Thread(() -> {

        System.out.println(Thread.currentThread().getName());
        resF = FuncF.calcPlusTen(resF, waitTimeF);
        System.out.println("value F +");
        calcF = false;
        synchronized (this) {
            notify();
        }
    });

    Thread threadG = new Thread(() -> {

        System.out.println(Thread.currentThread().getName());
        resG = FuncG.calcPlusFive(resG, waitTimeG);
        System.out.println("value G +");
        calcG = false;
        synchronized (this) {
            notify();
        }
    });

    public synchronized void defineResult() {

        while (calcF | calcG) {
            if (resF == 0 | resG == 0) {
                resFinal = 0;
                System.out.println("Result of computation is " + resFinal);
                for(Thread thread : curThreads) {
                    thread.stop();
                }
                return;
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        resFinal = resF * resG;

        System.out.println("Result of computation is " + resFinal);
    }

    public void run(int x, int waitTimeF, int waitTimeG) {
        System.out.println("mng started");

        resF = x;
        resG = x;
        this.waitTimeF = waitTimeF;
        this.waitTimeG = waitTimeG;

        Cancellation cancellation = new Cancellation();

        System.out.println("Enter X value: " + x);

        threadF.start();
        curThreads.add(threadF);
        threadG.start();
        curThreads.add(threadG);

        defineResult();

        cancellation.dispose();
    }

}