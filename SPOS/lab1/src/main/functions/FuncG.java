package main.functions;

public class FuncG {
    public static int calcPlusFive(int x, int waitTimeG) {

        try {
            Thread.sleep(waitTimeG);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x += 5;

        return x;
    }
}
