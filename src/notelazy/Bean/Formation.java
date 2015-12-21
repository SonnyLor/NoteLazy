/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Bean;
import java.util.ArrayList;
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

    public Formation() {
    }
    
    public Formation(ArrayList<Bloc> blocs ) {
        this.blocs = blocs;
    }

    public void setBlocs(ArrayList<Bloc> blocs) {
        this.blocs = blocs;
    }
    
    public void addBloc(Bloc bloc){
        this.blocs.add(bloc);
    }
    
    public void removeBloc(Bloc bloc){
        this.blocs.remove(bloc);
    }
}
