package task4;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Pool that block when it has not any items or it full
 */
public class BlockingObjectPool {
    private final ArrayBlockingQueue<Object> pool;

    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        this.pool = new ArrayBlockingQueue<>(size);
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public Object get() throws InterruptedException {
        return pool.take();
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public void take(Object object) throws InterruptedException {
        if (this.pool.offer(object)) {
            System.out.println("Object successfully added to pool");
        }
        this.pool.put(object);
    }
}
