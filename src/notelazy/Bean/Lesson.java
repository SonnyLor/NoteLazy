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
public class Lesson {

    @XmlAttribute(name ="Name")
    public String name;

    @XmlAttribute(name ="Weight")
    public double weight;

    public Lesson() {
    }

    public Lesson(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public Lesson(String name) {
        this.name = name;
        this.weight = 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
