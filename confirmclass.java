
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
//import javafx.stage.Modality;
import javafx.stage.Stage;

//import javafx.stage.Window;
public class confirmclass {
    static boolean Answer;
        public static boolean display(String title,String message) {
        Stage window=new Stage();
        Scene scene1;
        window.setTitle(title);
        window.setMinWidth(250);
       
        Button yesbtn=new Button("Yes");
        Button nobtn=new Button("No");
        Label label1=new Label(message); 
        yesbtn.setOnAction( new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            Answer=true;
            window.close();
            }
            
        });
        nobtn.setOnAction( new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            Answer=false;
            window.close();
            }
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(yesbtn,nobtn,label1);
        layout.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout, 400, 350);
        
        window.setScene(scene1);
        window.setTitle("concuremcy!");
        window.showAndWait();
        return Answer;
    }
   
}
        