package main;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class BakeryLock extends AbstractFixnumLock {
    private static volatile ArrayList<Integer> numbers;

    public BakeryLock(int maxThreadCount) {
        super(maxThreadCount);
        numbers = new ArrayList<>(Arrays.asList(new Integer[maxThreadCount]));
        Collections.fill(numbers, 0);
    }

    @Override
    public void lock() {
        int id = this.getId();
        int max = 0;
        for (int i = 0; i < numbers.size(); i++) {
            int current = numbers.get(i);
            if (current > max) {
                max = current ;
            }
        }

        numbers.set(id, max+1);

        for (int i = 0; i < numbers.size(); i++) {
            while (numbers.get(i) != 0
                    && (numbers.get(id) > numbers.get(i)
                    || (numbers.get(id).equals(numbers.get(i)) && id > i))){}
        }
    }

    @Override
    public void unlock() {
        numbers.set(this.getId(), 0);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
