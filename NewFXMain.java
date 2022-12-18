
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class NewFXMain extends Application {
    
    Scene scene1,scene2;

    Stage window;
    private  Inventory inventory ;
    private  Retailer retailer;
    private  Factory factory;

    public int buff = 0;

    public static void main(String[] args) {
    
        launch(args);
        
    }

    public NewFXMain() {
        this.inventory = null;
        this.retailer = null;
        this.factory = null;
    }
    
    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Program");

        GridPane Grid=new GridPane();
        Grid.setPadding(new Insets(10,10,10,10));
        Grid.setVgap(8);
        Grid.setHgap(10);

        Label label1=new Label("UserName");
        GridPane.setConstraints(label1,15, 18);

        TextField username=new TextField();
        GridPane.setConstraints(username,17, 18);

        Label label2=new Label("Password");
        GridPane.setConstraints(label2,15, 21);

        TextField password=new TextField();
        password.setPromptText("25****************");
        GridPane.setConstraints(password,17, 21);

        Label label3=new Label("Inventory Size");
        GridPane.setConstraints(label3,15, 24);

        TextField buffsize=new TextField();
        buffsize.setPromptText("Enter Your inventory size");
        GridPane.setConstraints(buffsize,17, 24);

        Button lgnin=new Button(" Sign in ");
        Label size = new Label();
        lgnin.setOnAction(event -> {
            String Name=new String();
            String pass=new String();
            Name=username.getText();
            pass=password.getText();
            buff=Integer.parseInt(buffsize.getText());
            size.setText(""+buff);
            System.out.println("It is " + buff);
            inventory.setSize(buff);
            inventory = inventory.getInstance();

            boolean chk=isInt(password,password.getText());

            System.out.println(chk);
            if( buff!=0){//Integer.parseInt(pass)==123 && Name=="mostafamohamed"
                stage.setScene(scene2);
            }
        });

        GridPane.setConstraints(lgnin,17, 26);

        Grid.getChildren().addAll(label1,username,label2,password,label3,buffsize,lgnin);

        scene1=new Scene(Grid,600,400);



        //scene 2 producer consumer table 
        scene2 = new Scene(new Group());
        scene2.getStylesheets().add("Style2Sheet.css");



        //int factoryCount = 10;
        //int retailerCount = 10;

        stage.setTitle("Producer Consumer");
        stage.setWidth(900);
        stage.setHeight(700);

//        TextField buffuer=new TextField("Set Buffer Size");//factory.bufferSize.size()
//        buffuer.setTranslateX(110);
//        buffuer.setTranslateY(-30);


        System.out.println(buff);


        inventory = inventory.getInstance();
        retailer = new Retailer(inventory);
        factory = new Factory(inventory);





        Label labe3 =new Label("Producer");
        labe3.setTranslateY(-80);
        labe3.setTranslateX(430);

        TextField NumProducer=new TextField();
        NumProducer.setPromptText("Enter Factories Number");
//        int retailerCount = NumConsumer.get;
//        int factoryCount = 10;
        NumProducer.setTranslateX(430);
        NumProducer.setTranslateY(10);

        Label labe4 =new Label("Consumer");
        labe4.setTranslateY(-100);
        labe4.setTranslateX(10);

        TextField NumConsumer=new TextField();
        NumConsumer.setPromptText("Enter Retailers Number");
        NumConsumer.setTranslateX(10);
        NumConsumer.setTranslateY(40);





//        final Label label = new Label("Buffer Size:");
//        label.setFont(new Font("Arial", 20));



        Button start=new Button("  Start  ");

        start.setTranslateX(400);
        start.setTranslateY(620);
        start.setOnAction(event -> {
            System.out.println("hi");
            int retailerCount = Integer.parseInt(NumConsumer.getText());
            int factoryCount = Integer.parseInt(NumProducer.getText());
            System.out.println(retailerCount + ",, " + factoryCount);
            retailer = new Retailer(inventory);
            factory = new Factory(inventory);




            for (int i = 0; i < retailerCount; i++) {
                Thread retailerThread = new Thread(retailer);
                retailerThread.setName("Retailer "+ (i+1));
                retailerThread.start();
            }

            for (int i = 0; i < factoryCount; i++) {
                Thread factoryThread = new Thread(factory);
                factoryThread.setName("Factory "+ (i+1));
                factoryThread.start();
            }

        });

        Button Stop=new Button("Stop Retailers");
        Stop.setTranslateX(200);
        Stop.setTranslateY(30);
        Stop.setOnAction(event -> retailer.stop());
         Button Stop2=new Button("Stop Factories");
        Stop2.setTranslateX(600);
        Stop2.setTranslateY(520);
        Stop2.setOnAction(event -> factory.stop());




         TableView<Product> table = new TableView(inventory.queue);
         TableView <Details> table2 = new TableView(inventory.details);
        table.setEditable(true);
        table2.setEditable(true);





        TableColumn<Product, String> firstNameCol = new TableColumn<>("Inventory");
        firstNameCol.setMinWidth(390);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Product,String>("Name"));

        TableColumn <Details,String>firstNameCo2 = new TableColumn<>("Details Of Inventory");
        firstNameCo2.setMinWidth(390);
        firstNameCo2.setCellValueFactory(new PropertyValueFactory<Details,String>("Name"));



        //table.setItems(getproduct());
        table.getColumns().addAll(firstNameCol);

        //table2.setItems(inventory.details);
//        table2.setItems(getproduct());

        table2.getColumns().addAll(firstNameCo2);

        table2.setTranslateX(410);
        table2.setTranslateY(-539);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(start,Stop2, table,NumConsumer,NumProducer,Stop,labe3,labe4,table2);

        ((Group) scene2.getRoot()).getChildren().addAll(vbox);
        scene1.getStylesheets().add("Styling.css");


        stage.setScene(scene1);
        stage.show();

    }
    
    
    private boolean isInt(TextField input,String message){
      try{
        int age =Integer.parseInt(input.getText());
        return true;
      }catch(NumberFormatException e){
          System.out.println("Error "+message+"is not a number");
          return false;
      }
    }
    
    
//     public void addintable(){
//        Product product=new Product();
//        product.setName("default input in table");
//        table.getItems().add(product);
//    }
//    public void deletefromtable(){
//        ObservableList<Product> productSelected, allproduct;
//        allproduct= table.getItems();
//        productSelected= table.getSelectionModel().getSelectedItems();
//        productSelected.forEach(allproduct::remove);
//    }
  

//    public ObservableList<String> getproduct(){
//        ObservableList<String> pro=FXCollections.observableArrayList();
//        for (String tab : inventory.details){
//            pro.add(tab);
//        }
////        for (String tab : retailer.details){
////            pro.add(tab);
////        }
//        return pro;
//    }
    
    
    
}





/*
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class NewFXMain extends Application {
    
    Scene scene1,scene2;
    private final Inventory inventory ;
    private final Retailer retailer;
    private final Factory factory;
    public TableView table = new TableView();
    public TableView table2 = new TableView();
    public static void main(String[] args) {
       
        launch(args);
        
    }

    public NewFXMain() {
        this.inventory = null;
        this.retailer = null;
        this.factory = null;
    }
    
    @Override
    public void start(Stage stage) {
        GridPane Grid=new GridPane();
        Grid.setPadding(new Insets(10,10,10,10));
        Grid.setVgap(8);
        Grid.setHgap(10);
        
        Label label1=new Label("UserName");
        GridPane.setConstraints(label1,15, 18);
        
        TextField username=new TextField();
        GridPane.setConstraints(username,17, 18);
        
        Label label2=new Label("Password");
        GridPane.setConstraints(label2,15, 21);
        
        TextField password=new TextField();
        password.setPromptText("25****************");
        GridPane.setConstraints(password,17, 21);
        
        Button lgnin=new Button(" Sign in ");
        GridPane.setConstraints(lgnin,17, 26);
        
        Grid.getChildren().addAll(label1,username,label2,password,lgnin);
        lgnin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                String Name=new String();
                String pass=new String();
                Name=username.getText();
                pass=password.getText();
                
                boolean chk=isInt(password,password.getText());
                
                    System.out.println(chk);
                if( true){//Integer.parseInt(pass)==123 && Name=="mostafamohamed"
                    stage.setScene(scene2);
                }
            }
        });
        scene1=new Scene(Grid,600,400);
           
        
        
        //scene 2 producer consumer table 
        scene2 = new Scene(new Group());
        scene2.getStylesheets().add("Style2Sheet.css");
  
        
        
        //int factoryCount = 10;
        //int retailerCount = 10;

        stage.setTitle("Producer Consumer");
        stage.setWidth(900);
        stage.setHeight(700);
        
        TextField buffuer=new TextField("100");//factory.bufferSize.size()
        buffuer.setText("5");
        buffuer.setTranslateX(110);
        buffuer.setTranslateY(-30);
        
        int buff=Integer.parseInt(buffuer.getText());
                Inventory inventory = new Inventory(buff);
                Retailer retailer = new Retailer(inventory);
                Factory factory = new Factory(inventory);
        
        
        
       
        
       
        Label labe3 =new Label("Producer");
        labe3.setTranslateY(-80);
        labe3.setTranslateX(430);
        
        TextField NumConsumer=new TextField("100");
//        int retailerCount = NumConsumer.get;
//        int factoryCount = 10;
        NumConsumer.setTranslateX(430);
        NumConsumer.setTranslateY(10);
        
        Label labe4 =new Label("Consumer");
        labe4.setTranslateY(-100);
        labe4.setTranslateX(10);
        
        TextField NumProdsumer=new TextField("100");
        NumProdsumer.setTranslateX(10);
        NumProdsumer.setTranslateY(40);
        
         
        

        
        final Label label = new Label("Buffer Size:");
        label.setFont(new Font("Arial", 20));
        
        
        
        Button start=new Button("  Start  ");
        start.setTranslateX(400);
        start.setTranslateY(620);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                int factoryCount = Integer.parseInt(NumProdsumer.getText());
                int retailerCount = Integer.parseInt(NumConsumer.getText());
                
                
                
                for (int i = 0; i < retailerCount; i++) {
                    Thread retailerThread = new Thread(retailer);
                    retailerThread.setName(i+1+"");
                    retailerThread.start();
                }

                for (int i = 0; i < factoryCount; i++) {
                    Thread factoryThread = new Thread(factory);
                    TableColumn <Product,String>firstNameCol = new TableColumn<>(" Process");
                    firstNameCol.setMinWidth(390);
                    firstNameCol.setCellValueFactory(new PropertyValueFactory<Product,String>("Name"));
                    
                    table.setItems(getproduct());
                    table.getColumns().addAll(firstNameCol);
                    
                    for ( int j = 0; j<table.getItems().size(); j++) {
                        table.getItems().clear(); 
                    }
                    factoryThread.setName(i+1+"");
                    factoryThread.start();
                }
                
            }
        });
        
        Button Stop=new Button("Stop");
        Stop.setTranslateX(200);
        Stop.setTranslateY(30);
        Stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               retailer.stop();
            }
        });
         Button Stop2=new Button("Stop2");
        Stop2.setTranslateX(600);
        Stop2.setTranslateY(580);
        Stop2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               factory.stop();
            }
        });
        table.setEditable(true);
        table2.setEditable(true);
        
        
        
        TableColumn <Product,String>firstNameCo2 = new TableColumn<>("Details Of Process");
        firstNameCo2.setMinWidth(390);
        firstNameCo2.setCellValueFactory(new PropertyValueFactory<Product,String>("Name"));
        
        
        
        table2.setItems(factory.details);
        table2.getColumns().addAll(firstNameCo2);
        
        table2.setTranslateX(410);
        table2.setTranslateY(-539);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(start,Stop2,label,buffuer, table,NumProdsumer,NumConsumer,Stop,labe3,labe4,table2);
 
        ((Group) scene2.getRoot()).getChildren().addAll(vbox);
        scene1.getStylesheets().add("Styling.css");
        
        
        stage.setScene(scene1);
        stage.show();
        
    }
    
    
    private boolean isInt(TextField input,String message){
      try{
        int age =Integer.parseInt(input.getText());
        return true;
      }catch(NumberFormatException e){
          System.out.println("Error "+message+"isnot a number");
          return false;
      }
    }
    
    
     public void addintable(){
        Product product=new Product();
        product.setName("default input in table");
        table.getItems().add(product);
    }
    public void deletefromtable(){
        ObservableList<Product> productSelected, allproduct;
        allproduct= table.getItems();
        productSelected= table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allproduct::remove);
    }
  

    public ObservableList<Product> getproduct(){
//        ObservableList<Product> products=FXCollections.observableArrayList();
//        for (Integer item: inventory.queue) {
//            products.add(new Product(item+""));
//            
//        }
       
        return factory.items;
    }
    
    
    
}
*/





