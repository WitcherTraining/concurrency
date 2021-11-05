package task2;

public class SquareRootPrinter extends Thread {
    public void run() {
        for (; ; ) {
            synchronized (Operation.class) {
                Operation.getInstance().sumAndPrintSquareRoots();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
