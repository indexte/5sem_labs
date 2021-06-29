package main;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;

public class ImprovedBakeryLock extends AbstractFixnumLock {
    private final int MAX_TICKETS = Integer.MAX_VALUE;
    private static volatile ArrayList<AtomicInteger> ticket;
    private static volatile ArrayList<AtomicBoolean> entering;

    public ImprovedBakeryLock(int maxThreadCount) {
        super(maxThreadCount);
        ticket = new ArrayList<>(Arrays.asList(new AtomicInteger[maxThreadCount]));
        entering = new ArrayList<>(Arrays.asList(new AtomicBoolean[maxThreadCount]));

        Collections.fill(ticket, new AtomicInteger(0));
        Collections.fill(entering, new AtomicBoolean(false));
    }

    @Override
    public  void lock() {

        int id = this.getId();

        entering.set(id, new AtomicBoolean(true));
        int max = 0;
        for (int i = 0; i < ticket.size(); i++) {
            int current = ticket.get(i).get();
            if (current > max) {
                max = current;
            }
        }

        if (max == MAX_TICKETS - 1) {

            ticket.set(id, new AtomicInteger(0));

        } else {
            ticket.set(id, new AtomicInteger(1 + max));
        }

        entering.set(id, new AtomicBoolean(false));


        for (int i = 0; i < ticket.size(); i++) {
            if (i != id) {
                while (entering.get(i).get()) {
                    Thread.yield();
                }

                while (ticket.get(i).get() != 0
                        && (ticket.get(id).get() > ticket.get(i).get()
                        || (ticket.get(id).equals(ticket.get(i)) && id > i))) {
                    Thread.yield();

                }
            }
        }

    }


    @Override
    public void unlock() {

        ticket.set(this.getId(), new AtomicInteger(0));
    }

    private synchronized void setUsedTickets() {
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
