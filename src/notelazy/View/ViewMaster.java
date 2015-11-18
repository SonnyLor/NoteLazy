/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package notelazy.View;

import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import notelazy.Ctrl.*;

/**
 *
 * @author sonny
 */
public class ViewMaster extends Application {

    private Stage primaryStage;
    Locale locale;

    public ViewMaster() {
    }
    public void goToMainView() {
        try {
            MainViewController mainView = (MainViewController) replaceSceneContent("/notelazy/View/MainView.fxml");
            mainView.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ViewMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void goToEditLessonView() {
        try {
            LessonsController mainView = (LessonsController) replaceSceneContent("/notelazy/View/Lessons.fxml");
            mainView.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ViewMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void goToEditGradesView() {
        try {
            GradesController mainView = (GradesController) replaceSceneContent("/notelazy/View/Grades.fxml");
            mainView.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ViewMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void goToHelpView() {
        try {
            MainViewController mainView = (MainViewController) replaceSceneContent("/notelazy/View/MainView.fxml");
            mainView.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ViewMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void goToExportView() {
        try {
            MainViewController mainView = (MainViewController) replaceSceneContent("/notelazy/View/MainView.fxml");
            mainView.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ViewMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void goToImportView() {
        try {
            MainViewController mainView = (MainViewController) replaceSceneContent("/notelazy/View/MainView.fxml");
            mainView.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ViewMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToSettingsView() {
        try {
            SettingsController settings = (SettingsController) replaceSceneContent("/notelazy/View/Settings.fxml");
            settings.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ViewMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        locale = new Locale("en", "En");
        goToMainView();
        primaryStage.show();
    }

    public void setLocale(Locale locale){
        this.locale = locale;
    }

    public void setTitle(String title,String subTitle){
        primaryStage.setTitle(title +" - " +subTitle);
    }
    
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("notelazy.View.Bundles.Bundles", locale));
        InputStream in = ViewMaster.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(ViewMaster.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 960, 530);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        return (Initializable) loader.getController();
    }

}
