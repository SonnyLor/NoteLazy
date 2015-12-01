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
    @XmlAttribute
    private String name;

    public Lesson() {
    }

    public Lesson(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
