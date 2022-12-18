import java.io.File;
import java.io.FileWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Factory implements Runnable {
    private final Inventory inventory;
    private Product product;

    private Details details;
//    public ObservableList<Product> items=FXCollections.observableArrayList();

    public ObservableList<Product> bufferSize=FXCollections.observableArrayList(); 
    private volatile boolean flag;
    private volatile int counter = 0;



    public Factory(Inventory inventory) {
        this.inventory = inventory;
        this.product = null;
        this.details = null;
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
            
            inventory.mutex.acquire();

            if(!flag) {
                inventory.mutex.release();
                break;
            }


            product = new Product();
            product.setName("Product " + num);
            details = new Details();
            details.setName(Thread.currentThread().getName() + " produced " + product.getName());


            inventory.add(product);
           //Thread.sleep(1000);
            System.out.println(Thread.currentThread().getId() + " producing " + product.getName());
            
             



            inventory.addDetails(details);
            Thread.sleep(1000);
//             for (Integer item: inventory.queue) {
//                items.add(new Product(item+""));
//             }
            
          
            
            System.out.println(Thread.currentThread().getName() + " produced " + num);
            System.out.println("\nThe buffer now : " + inventory.getBuffer());
            
        //   bufferSize.add(new Product(inventory.getBuffer()));
            
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
