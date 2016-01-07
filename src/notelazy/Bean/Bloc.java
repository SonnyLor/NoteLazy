/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Bean;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.*;

/**
 *
 * @author sonny
 */
public class Bloc {
    private final SimpleStringProperty name;
    @XmlElement(name = "Lesson")
    public ArrayList<Lesson> lessons;

    public Bloc(String name, ArrayList<Lesson> lessons) {
        this.name = new SimpleStringProperty(name);
        this.lessons = lessons;
    }

    public Bloc(String name) {
        this.name = new SimpleStringProperty(name);
        this.lessons = new ArrayList<Lesson>();
    }
    
    public Bloc() {
        this.lessons = new ArrayList<Lesson>();
        this.name = new SimpleStringProperty();
    }
    
    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }
    
    public boolean removeLesson(Lesson lesson){
        return lessons.remove(lesson);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    @XmlAttribute(name ="Name")
    public String getName() {
        return name.get();
    }
    
    public String toString(){
        return name.get();
    }

    public SimpleStringProperty getNameProp() {
        return name;
    }
    
    
}
