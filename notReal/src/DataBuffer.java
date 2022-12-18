import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class DataBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final Queue<Integer> consumed = new LinkedList<>();
    private final int max;
    public Semaphore mutex = new Semaphore(1,true);
    public Semaphore full = new Semaphore(0,true);
    public Semaphore empty;
    public static int counter = 0;

    DataBuffer(int size) {
        this.max = size;

        empty = new Semaphore(max,true);
    }

    public void add(int num) {

            queue.add(num);

    }

    public int remove() {

            return queue.poll();

    }

    public String getBuffer() {
        return queue.toString();
    }

    public void addConsumed(int num) {
        consumed.add(num);
       this.counter++;
    }

    public String getConsumed() {
        return consumed.toString();
    }


    public boolean isFull() {
        return queue.size() == max;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
