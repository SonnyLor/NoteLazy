/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Bean;

import javax.xml.bind.annotation.*;

/**
 *
 * @author sonny
 */
public class Note {
    
    @XmlAttribute(name ="Name")
    public Lesson lesson;

    @XmlAttribute(name ="Weight")
    public double weight;

    @XmlAttribute(name ="Weight")
    public double note;

    public Note() {
    }

    public Note(Lesson lesson, double note, double weight) {
        this.lesson = lesson;
        this.weight = weight;
    }
}
