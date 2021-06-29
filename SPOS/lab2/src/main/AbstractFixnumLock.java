package main;

public abstract class AbstractFixnumLock implements FixnumLock {

    private final Thread[] threads;

    public AbstractFixnumLock(int maxThreadCount) {
        this.threads = new Thread[maxThreadCount];
    }

    @Override
    public int getId() {
        for (int i = 0; i < this.threads.length; ++i) {
            if (this.threads[i] == Thread.currentThread()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int register(Thread thread) {
        int firstUnregisteredIndex = -1;

        for (int i = 0; i < this.threads.length; ++i) {
            if (this.threads[i] == thread) {
                return i;
            }

            if (firstUnregisteredIndex == -1 && this.threads[i] == null) {
                firstUnregisteredIndex = i;
            }
        }

        if (firstUnregisteredIndex != -1) {
            this.threads[firstUnregisteredIndex] = thread;
        }

        return firstUnregisteredIndex;
    }

    @Override
    public void unregister(Thread thread) {
        for (int i = 0; i < this.threads.length; ++i) {
            if (this.threads[i] == thread) {
                this.threads[i] = null;
                break;
            }
        }
    }
}
