package task3;

import java.util.PriorityQueue;
import java.util.Queue;

public class MessageBus {
    private final Queue<Integer> queue = new PriorityQueue<>();
    private final int capacity = 2;

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (queue.size() == capacity)
                    wait();

                System.out.println("Producer produced-" + value);
                queue.add(value++);
                notify();
                Thread.sleep(1000);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (queue.size() == 0)
                    wait();

                int val = queue.remove();
                System.out.println("Consumer consumed-" + val);
                notify();
                Thread.sleep(1000);
            }
        }
    }
}
