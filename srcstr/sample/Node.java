package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private StringProperty wage ;


    public void setWage(String value) { wageProperty().set(value); }
    public String getWage() { return wageProperty().get(); }
    public StringProperty wageProperty() {
        if (wage == null) wage = new SimpleStringProperty(this, "firstName");
        return wage;
    }

    int value;
    int stopien;
    int cords[]=new int[2];
    Node(int val)
    {
        value = val;
        stopien = 0;
    }
    List<Integer> sonsiad = new ArrayList<Integer>();
    void addIn(int sth){
        sonsiad.add(sth);
        stopien++;
    }
    void setCords(int x, int y)
    {
        cords[0] = x;
        cords[1] = y;
    }

}
