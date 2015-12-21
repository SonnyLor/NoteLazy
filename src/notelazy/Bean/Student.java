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
@XmlRootElement(name = "Student")
public class Student {
    @XmlElement(name = "Result")
    public ArrayList<Note> result;
}
