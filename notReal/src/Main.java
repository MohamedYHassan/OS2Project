public class Main {
    public static void main(String[] args)  {

        int factoryCount = 10;
        int retailerCount = 10;
        Inventory inventory = new Inventory(100);
        Retailer retailer = new Retailer(inventory);
        Factory factory = new Factory(inventory);


        for (int i = 0; i < retailerCount; i++) {
            Thread retailerThread = new Thread(retailer);
            retailerThread.setName(i+1+"");
            retailerThread.start();
        }

        for (int i = 0; i < factoryCount; i++) {
            Thread factoryThread = new Thread(factory);
            factoryThread.setName(i+1+"");
            factoryThread.start();
        }



    }
}
