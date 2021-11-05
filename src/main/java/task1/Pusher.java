package task1;

public class Pusher extends Thread {

    public void run() {
        for (int i = 0; i < 30000; i++) {
            Operation.putToMap(i, i);
        }
    }
}
