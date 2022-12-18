import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Details {
    private final StringProperty  name = new SimpleStringProperty();



    public Details(String name,String details) { this.name.set(name);  }
    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {return name.get(); }



    public Details() {
        this.name.set("");
    }



}
/*
 */


