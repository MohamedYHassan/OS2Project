public class Retailer implements Runnable {
    private final Inventory inventory;
    private volatile boolean flag;

    private volatile int counter = 0;

    public Retailer(Inventory inventory) {
        this.inventory = inventory;
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

            inventory.full.acquire();
            System.out.println(Thread.currentThread().getName() + " Wants to consume");
            inventory.mutex.acquire();



            if(!flag) {
                inventory.mutex.release();
                break;
            }


            num = inventory.remove();
//            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " consuming " + num) ;
//            Thread.sleep(3000);
            useNum(num);
//            if(counter == 10) {
//                System.out.println(Thread.currentThread().getName() + " is the last consumer\n");
//                System.out.println("Elements consumed are : " + inventory.getConsumed());
//               // Thread.sleep(5000);
//                flag = false;
//            }
//            counter++;

            inventory.mutex.release();
            inventory.empty.release();
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
        inventory.addConsumed(num);
    }


}
