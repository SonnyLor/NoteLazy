/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Ctrl;

import java.io.File;
import java.net.URL;
import java.sql.Blob;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import notelazy.Bean.*;
import notelazy.Worker.*;
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
        Formation form = new Formation();
        Bloc bloc1 = new Bloc("Système d'information");
        bloc1.addLesson(new Lesson("Programmation avancé 2"));
        bloc1.addLesson(new Lesson("Système d'information 2"));
        form.addBloc(bloc1);
        Bloc bloc2 = new Bloc("Essais");
        bloc2.addLesson(new Lesson("Programmation avancé 3"));
        bloc2.addLesson(new Lesson("Système d'information 4"));
        form.addBloc(bloc2);
        XMLLoader loader = new XMLLoader();
        loader.saveFormationToFile(new File("Essais.xml"), form);
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
