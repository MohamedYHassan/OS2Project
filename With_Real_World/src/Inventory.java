import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Inventory {



        public  ObservableList<Product> queue = FXCollections.observableArrayList();
        private final Queue<Product> consumed = new LinkedList<Product>();


         public ObservableList<Details> details= FXCollections.observableArrayList();
        private  int max;
        public Semaphore mutex = new Semaphore(1,true);
        public Semaphore full = new Semaphore(0,true);
        public Semaphore empty;
        public static int counter = 0;

        private final static Inventory INSTANCE = new Inventory();



       private Inventory(int size) {
            this.max = size;


        }

        private Inventory(){};

       public static Inventory getInstance(){
           return INSTANCE;
       }




    public void add(Product product) {

            queue.add(product);

        }

        public void addDetails(Details detail){
           details.add(detail);
        }

        public Product remove() {

            return queue.remove(0);

        }

        public String getBuffer() {
            return queue.toArray().toString();
        }

        public void addConsumed(Product product) {
            consumed.add(product);
            this.counter++;
        }

        public String getConsumed() {
            return consumed.toString();
        }

        public void setSize(int size) { this.max = size; empty = new Semaphore(max,true);  }

        public boolean isFull() {
            return queue.size() == max;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
