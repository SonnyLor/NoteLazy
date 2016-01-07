/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Bean;

import java.util.ArrayList;
import javafx.beans.property.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author sonny
 */
public class Lesson {

    private final SimpleStringProperty name;
    private final SimpleDoubleProperty weight;
    @XmlElement(name = "Note")
    public ArrayList<Note> notes = new ArrayList<>();

    public Lesson() {
         this.name =  new SimpleStringProperty();
         this.weight =  new SimpleDoubleProperty();
    }

    public Lesson(String name, double weight) {
        this.name =  new SimpleStringProperty(name);
        this.weight = new SimpleDoubleProperty(weight);
    }

    public Lesson(String name) {
        this.name =  new SimpleStringProperty(name);
        this.weight = new SimpleDoubleProperty(1);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @XmlAttribute(name ="Name")
    public String getName() {
        return name.get();
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    @XmlAttribute(name ="Weight")
    public double getWeight() {
        return weight.doubleValue();
    }

    public SimpleStringProperty getNameProp() {
        return name;
    }

    public SimpleDoubleProperty getWeightProp() {
        return weight;
    }
    
    public String toString(){
        return getName();
    }
}
