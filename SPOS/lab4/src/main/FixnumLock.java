package main;

import java.util.concurrent.locks.Lock;

public interface FixnumLock extends Lock {
    int getId();

    int register(Thread thread);

    void unregister(Thread thread);
}
