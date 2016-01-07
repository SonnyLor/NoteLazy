/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Bean;

import javafx.beans.property.SimpleDoubleProperty;
import javax.xml.bind.annotation.*;

/**
 *
 * @author sonny
 */
public class Note {

    /*@XmlElement(name = "Lesson")
    public Lesson lesson;*/

    private final SimpleDoubleProperty weight;

    private final SimpleDoubleProperty note;

    public Note(double note, double weight) {
        this.weight = new SimpleDoubleProperty(weight);
        this.note = new SimpleDoubleProperty(note);
    }

    public Note() {
        this.weight = new SimpleDoubleProperty();
        this.note = new SimpleDoubleProperty();
     }

    /*public Lesson getLesson() {
        return lesson;
    }
*/
   /* public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }*/

    @XmlAttribute(name = "Weight")
    public double getWeight() {
        return weight.get();
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    @XmlAttribute(name = "Note")
    public double getNote() {
        return note.get();
    }

    public void setNote(double note) {
        this.note.set(note);
    }

    public SimpleDoubleProperty getWeightProp() {
        return weight;
    }

    public SimpleDoubleProperty getNoteProp() {
        return note;
    }
    
    
}
