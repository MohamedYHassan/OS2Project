/*
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
    
    public TableView table = new TableView();
    public static void main(String[] args) {
        launch(args);
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
            public void handle(ActionEvent event) {
                String Name=new String();
                String pass=new String();
                Name=username.getText();
                pass=password.getText();
                
                boolean chk=isInt(password,password.getText());
                
                    System.out.println(chk);
                if( true){
                    System.out.println("fffffffffffuck");
                    stage.setScene(scene2);
                }
            }
        });
        scene1=new Scene(Grid,600,400);
           
        
        
        //scene 2 producer consumer table 
        scene2 = new Scene(new Group());
        scene2.getStylesheets().add("Style2Sheet.css");
        stage.setTitle("Producer Consumer");
        stage.setWidth(900);
        stage.setHeight(600);
        
        Button Stop=new Button("Stop");
        Stop.setTranslateX(200);
        
        Label labe3 =new Label("Producer");
        labe3.setTranslateY(-400);
        labe3.setTranslateX(440);
        
        TextField NumConsumer=new TextField("default Number 5000");
        NumConsumer.setTranslateX(400);
        NumConsumer.setTranslateY(-300);
        
        Label labe4 =new Label("Consumer");
        labe4.setTranslateY(-200);
        labe4.setTranslateX(440);
        
        TextField NumProdsumer=new TextField("default");
        NumProdsumer.setTranslateX(400);
        NumProdsumer.setTranslateY(-40);
        
        final Label label = new Label("Processes");
        label.setFont(new Font("Arial", 20));
 
        
        
        table.setEditable(true);
        
        TableColumn <Product,String>firstNameCol = new TableColumn<>("First process");
        firstNameCol.setMinWidth(390);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Product,String>("Name"));
        
        table.setItems(getproduct());
        table.getColumns().addAll(firstNameCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table,NumProdsumer,NumConsumer,Stop,labe3,labe4);
 
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
        ObservableList<Product> products=FXCollections.observableArrayList();
        products.add(new Product("item1"));
        products.add(new Product("item2"));
        products.add(new Product("item3"));
        products.add(new Product("item4"));
        products.add(new Product("item5"));
        
        return products;
    }
    
    
    
}






/*Integer.parseInt(pass)==123 && Name=="mostafamohamed"*/