package main.functions;

public class FuncF {
    public static int calcPlusTen(int x, int waitTimeF) {

        try {
            Thread.sleep(waitTimeF);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x += 10;

        return x;
    }
}