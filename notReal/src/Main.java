
public class Main {
    public static void main(String[] args)  {

        int producerCount = 5;
        int consumerCount = 10;
        DataBuffer dataBuffer = new DataBuffer(100);
        Consumer consumer = new Consumer(dataBuffer);
        Producer producer = new Producer(dataBuffer);

        for (int i = 0; i < consumerCount; i++) {
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
        }

        for (int i = 0; i < producerCount; i++) {
            Thread producerThread = new Thread(producer);
            producerThread.start();
        }
    }
}
















