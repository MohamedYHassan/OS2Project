

public class Producer implements Runnable {
    private final DataBuffer dataBuffer;
    private volatile boolean flag;
    private volatile int counter = 0;


    public Producer(DataBuffer dataBuffer) {
        this.dataBuffer = dataBuffer;
        flag = true;
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void produce() throws InterruptedException {
        while(flag) {

            int num = generateNum();

            dataBuffer.empty.acquire();
         // Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + " Wants to produce");
            //Thread.sleep(2000);
            dataBuffer.mutex.acquire();

            if(!flag) {
                dataBuffer.mutex.release();
                break;
            }


            dataBuffer.add(num);
//            Thread.sleep(3000);
//            System.out.println(Thread.currentThread().getId() + " producing " + num);
//            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getId() + " produced " + num);
            System.out.println("\nThe buffer now : " + dataBuffer.getBuffer());
//            Thread.sleep(3000);

//            if(counter==5) {
//              System.out.println(Thread.currentThread().getId() + " is the last producer");
              //Thread.sleep(5000);
//                flag = false;
//            }
            counter++;
            dataBuffer.mutex.release();
            dataBuffer.full.release();
        }
    }

    public void stop() {
        flag = false;
    }

    public int generateNum() {
        int num = (int) (Math.random() * 100);
        return num;
    }

}
