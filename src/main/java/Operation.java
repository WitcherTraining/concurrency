import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Operation {
    ////// tried only with HashMap -> ConcurrentModificationException() /////////
//    private static Map<Integer, Integer> numbers1 = new HashMap<>();

    ////// works with synchronized operational methods /////////
//    private static Map<Integer, Integer> numbers = Collections.synchronizedMap(numbers1);
//    private static Map<Integer, Integer> mySynchronizedMap = new SynchronizedHashMap<>(numbers1);

    private static ConcurrentHashMap<Integer, Integer> numbers1 = new ConcurrentHashMap<>();

    public static void putToMap(int key, int value) {
        numbers1.put(key, value);
    }

    public static void sumNumbers() {
        long sum = 0L;
        for (int value : numbers1.values()) {
            sum += value;
        }
    }
}
