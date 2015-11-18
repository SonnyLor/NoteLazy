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
public class MainViewController implements Initializable {

    private ViewMaster application;
    private ResourceBundle rb;
    
    public void setApp(ViewMaster application){
        this.application = application;
        application.setTitle(rb.getString("title"),rb.getString("title.main"));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
    }  
    
    public void importData(){
        application.goToExportView();
    }
    public void exportData(){
        application.goToExportView();
    }
    public void settings(){
        application.goToSettingsView();
    }
    public void close(){
        
    }
    public void editLessons(){
        application.goToEditLessonView();
    }
    public void editGrade(){
        application.goToEditGradesView();
    }
    public void help(){
        application.goToHelpView();
    }
}
