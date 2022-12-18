import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private final StringProperty  name = new SimpleStringProperty();
    private final StringProperty details = new SimpleStringProperty();

    
    public Product(String name,String details) { this.name.set(name);  this.details.set(details);}
    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {return name.get(); }

    public StringProperty detailsProperty() { return details;}

    public void setDetails(String details){ this.details.set(details);}

    public String getDetails() { return details.get();}

    public Product() {
        this.name.set("");
    }
   
    
    
}
/*
*/


