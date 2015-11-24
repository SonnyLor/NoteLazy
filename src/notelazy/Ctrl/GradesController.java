/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Ctrl;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import notelazy.View.ViewMaster;

/**
 * FXML Controller class
 *
 * @author sonny
 */
public class GradesController implements Initializable {

    private ViewMaster application;
    private ResourceBundle rb;
    
    public void setApp(ViewMaster application){
        this.application = application;
        application.setTitle(rb.getString("title"),rb.getString("title.edition.grade"));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
    }    
    
    public void selectLesson(){
        
    }
    
    public void back(){
        application.goToMainView(); 
    }
    
    public void addGrade(){
        
    }
    
    public void deleteGrade(){
        
    }
    
}
