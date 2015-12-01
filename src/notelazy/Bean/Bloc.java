/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Bean;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author sonny
 */
public class Bloc {
    @XmlAttribute(name ="Name")
    private String name;
    @XmlElement(name = "Lesson")
    private ArrayList<Lesson> lessons;

    public Bloc(String name, ArrayList<Lesson> lessons) {
        this.name = name;
        this.lessons = lessons;
    }

    public Bloc(String name) {
        this.name = name;
        this.lessons = new ArrayList<Lesson>();
    }
    
    public Bloc() {
        this.lessons = new ArrayList<Lesson>();
        this.name = new String();
    }
    
    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }
    
    public void removeLesson(Lesson lesson){
        lessons.remove(lesson);
    }
}
