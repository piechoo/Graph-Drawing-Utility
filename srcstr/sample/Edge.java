package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Edge {
    private final IntegerProperty value = new SimpleIntegerProperty(this, "value");
    private final IntegerProperty from = new SimpleIntegerProperty(this, "from");
    private final IntegerProperty to = new SimpleIntegerProperty(this, "to");
    private  StringProperty wage ;


    public void setWage(String value) { wageProperty().set(value); }
    public String getWage() { return wageProperty().get(); }
    public StringProperty wageProperty() {
        if (wage == null) wage = new SimpleStringProperty(this, "firstName");
        return wage;
    }


    //boolean kierownica;
    Edge(){

    }
    Edge(int value,int from,int to, String wage){
        this.value.set(value);
        this.from.set(from);
        this.to.set(to);
        setWage(wage);
    }
    public final IntegerProperty fromProperty() {
        return this.from;
    }
    public final IntegerProperty toProperty() {
        return this.to;
    }
    public final IntegerProperty valueProperty() {
        return this.value;
    }
    void setValue(Integer value)
    {
        this.valueProperty().set(value);
    }
    void setFrom(Integer from)
    {
        this.fromProperty().set(from);
    }
    void setTo(Integer to)
    {
        this.toProperty().set(to);
    }
    Integer getValue() {
        return this.valueProperty().get();
    }
    Integer getFrom() {
        return this.fromProperty().get();
    }
    Integer getTo() {
        return this.toProperty().get();
    }

}
