/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Bean;

import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sonny
 */
@XmlRootElement(name = "Formation")
public class Formation {

    @XmlElement(name = "Bloc")
    public ArrayList<Bloc> blocs = new ArrayList<Bloc>();
    private SimpleIntegerProperty min;
    private SimpleIntegerProperty max;

    public Formation() {
        blocs = new ArrayList<>();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }

    public Formation(ArrayList<Bloc> blocs, int min, int max) {
        this.blocs = blocs;
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    @XmlAttribute(name = "max")
    public int getMax() {
        return max.get();
    }

    @XmlAttribute(name = "min")
    public int getMin() {
        return min.get();
    }
    
    public void setMin(int min) {
        this.min.set(min);
    }
    
    public void setMax(int max) {
        this.max.set(max);
    }
    
    public SimpleIntegerProperty getMaxProp() {
        return max;
    }

    public SimpleIntegerProperty getMinProp() {
        return min;
    }

    public void setBlocs(ArrayList<Bloc> blocs) {
        this.blocs = blocs;
    }

    public void addBloc(Bloc bloc) {
        this.blocs.add(bloc);
    }

    public void removeBloc(Bloc bloc) {
        this.blocs.remove(bloc);
    }
}
