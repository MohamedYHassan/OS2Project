

public class Consumer implements Runnable {
    private final DataBuffer dataBuffer;
    private volatile boolean flag;
    private volatile int counter = 0;

    public Consumer(DataBuffer dataBuffer) {
        this.dataBuffer = dataBuffer;
        flag = true;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() throws InterruptedException {
        while(flag) {

            int num;

            dataBuffer.full.acquire();
            System.out.println(Thread.currentThread().getId() + " Wants to consume");
            dataBuffer.mutex.acquire();

            if(!flag) {
                dataBuffer.mutex.release();
                break;
            }


            num = dataBuffer.remove();
//            Thread.sleep(3000);
           System.out.println(Thread.currentThread().getId() + " consuming " + num) ;
//            Thread.sleep(3000);
            useNum(num);
//            if(counter == 5) {
//                System.out.println(Thread.currentThread().getId() + " is the last consumer\n");
//                System.out.println("Elements consumed are : " + dataBuffer.getConsumed());
               // Thread.sleep(5000);
//                flag = false;
//            }
            counter++;

            dataBuffer.mutex.release();
            dataBuffer.empty.release();
//            Thread.sleep(3000);
//
//            System.out.println(Thread.currentThread().getId() + " consumed" + num);
//            Thread.sleep(3000);

        }
    }

    public void stop() {
        flag = false;
    }

    public void useNum(int num) {
        dataBuffer.addConsumed(num);
    }

}
