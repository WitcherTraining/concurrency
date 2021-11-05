package task1;

public class Operator extends Thread {
    public void run() {
        double startTime = System.currentTimeMillis();
        for (int i = 0; i < 30000; i++) {
            Operation.sumNumbers();
        }
        double endTime = System.currentTimeMillis();
        System.out.println(String.format("Execution time: %.2f seconds", (endTime - startTime)/1000));
    }
}
