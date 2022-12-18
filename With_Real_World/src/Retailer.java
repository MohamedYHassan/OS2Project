
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Retailer implements Runnable {
    private final Inventory inventory;
    private Product product;

    private Details details;
    private volatile boolean flag;
    private volatile int counter = 0;

    public Retailer(Inventory inventory) {
        this.inventory = inventory;
        this.product = null;
        this.details = null;
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



            inventory.full.acquire();
           // System.out.println(Thread.currentThread().getName() + " Wants to consume");
            inventory.mutex.acquire();



            if(!flag) {
                inventory.mutex.release();
                break;
            }


            product = inventory.remove();
            details = new Details();
            details.setName(Thread.currentThread().getName() + " consumed " + product.getName());
            
            Thread.sleep(1000);
            
            System.out.println(Thread.currentThread().getName() + " consuming " + product.getName()) ;
            inventory.addDetails(details);
            Thread.sleep(1000);
            
            useNum(product);
//            if(counter == 10) {
//                System.out.println(Thread.currentThread().getName() + " is the last consumer\n");
//                System.out.println("Elements consumed are : " + inventory.getConsumed());
//               // Thread.sleep(5000);
//                flag = false;
//            }
//            counter++;

            inventory.mutex.release();
            inventory.empty.release();
            Thread.sleep(1000);
//
            System.out.println(Thread.currentThread().getId() + " consumed" + product.getName());
            Thread.sleep(1000);




        }
    }

    public void stop() {
        flag = false;
    }

    public void useNum(Product product) {
        inventory.addConsumed(product);
    }


}
