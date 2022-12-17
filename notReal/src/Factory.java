public class Factory implements Runnable{
    private final Inventory inventory;
    private volatile boolean flag;
    private volatile int counter = 0;



    public Factory(Inventory inventory) {
        this.inventory = inventory;
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

            inventory.empty.acquire();
            //   Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " Wants to produce");
            //Thread.sleep(2000);
            inventory.mutex.acquire();

            if(!flag) {
                inventory.mutex.release();
                break;
            }




            inventory.add(num);
//            Thread.sleep(3000);
//            System.out.println(Thread.currentThread().getId() + " producing " + num);
//            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " produced " + num);
            System.out.println("\nThe buffer now : " + inventory.getBuffer());
//            Thread.sleep(3000);

//            if(counter==20) {
//                System.out.println(Thread.currentThread().getName() + " is the last producer");
//               //Thread.sleep(5000);
//                flag = false;
//            }
//            counter++;
            inventory.mutex.release();
            inventory.full.release();




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
