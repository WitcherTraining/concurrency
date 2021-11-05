package task2;

public class RandomWriter extends Thread {
    public void run() {
        for (; ; ) {
            synchronized (Operation.class) {
                Operation.getInstance().addToList();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
