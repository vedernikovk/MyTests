package concurrent.old;

public class RMWO {

    private int readers = 0;
    private boolean writer = false;

    public static void main(String[] args) {

        RMWO inst = new RMWO();

        inst.obtainReadLock();
        inst.obtainReadLock();
        inst.releaseReadLock();
        inst.releaseReadLock();

        inst.obtainWriteLock();
        inst.releaseWriteBlock();

        inst.obtainReadLock();
        inst.obtainWriteLock();
    }

    synchronized public void obtainReadLock() {
        while (writer) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        readers++;
    }

    synchronized public void releaseReadLock() {
        readers--;
        notifyAll();
    }

    synchronized public void obtainWriteLock() {
        while ((readers != 0) || (writer)) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        writer = true;
    }

    synchronized public void releaseWriteBlock() {
        writer = false;
        notifyAll();
    }
}
