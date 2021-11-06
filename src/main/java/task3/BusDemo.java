package task3;

public class BusDemo {
    public static void main(String[] args) throws InterruptedException {
        final MessageBus messageBus = new MessageBus();

        Thread t1 = new Thread(() -> {
            try {
                messageBus.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                messageBus.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
