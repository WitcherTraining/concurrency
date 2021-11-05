package task2;

public class SumPrinter extends Thread {
    public void run() {
        for (; ; ) {
            synchronized (Operation.class) {
                Operation.getInstance().sumNumbers();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
