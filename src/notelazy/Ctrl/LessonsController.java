/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Ctrl;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import notelazy.Bean.Bloc;
import notelazy.NoteLazy;
import notelazy.View.ViewMaster;
import notelazy.Worker.XMLLoader;

/**
 * FXML Controller class
 *
 * @author sonny
 */
public class LessonsController implements Initializable {

    private TextField lesson;
    private TextField weight;
    private TextField newBloc;
    private MenuButton blocs;
    
    private ViewMaster application;
    private ResourceBundle rb;
    
    public void setApp(ViewMaster application){
        this.application = application;
        application.setTitle(rb.getString("title"),rb.getString("title.edition.lesson"));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            this.rb=rb;
    }    
    
    public void addAndSelect(){
        Bloc bloc = new Bloc(newBloc.getText());
        NoteLazy.handler.formation.addBloc(bloc);
        XMLLoader.saveFormationToFile(new File(NoteLazy.FormationPath), NoteLazy.handler.formation);
    }
    public void add(){
        
    }
    public void deleteSelected(){
        
    }
    public void back(){
        application.goToMainView();
    }
}
