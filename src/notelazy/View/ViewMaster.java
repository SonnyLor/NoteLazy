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
import javafx.concurrent.Service;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import notelazy.Ctrl.*;
import notelazy.NoteLazy;
import notelazy.Worker.DataHandler;

/**
 *
 * @author sonny
 */
public class ViewMaster {

    private Stage primaryStage;
    private Locale locale;
    private Service<Void> loaderStudent;
    private Service<Void> loaderFormation;

    public ViewMaster(Stage primaryStage) {
        this.primaryStage = primaryStage;
        locale = new Locale("fr", "CH");
        goToMainView();
        primaryStage.show();
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

    public void goToSettingsView() {
        try {
            SettingsController settings = (SettingsController) replaceSceneContent("/notelazy/View/Settings.fxml");
            settings.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ViewMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveFormation() {
        System.out.println("Sauvegarde des formations");
        DataHandler handler = new DataHandler(NoteLazy.FormationPath,false,false);
        handler.start();
    }

    /*@Override
     public void start(Stage primaryStage) throws Exception {
     }*/
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setTitle(String title, String subTitle) {
        primaryStage.setTitle(title + " - " + subTitle);
    }

    public void setErrorMEssage(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public ResourceBundle getRessourceBundle() {
        return ResourceBundle.getBundle("notelazy.View.Bundles.Bundles", locale);
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(getRessourceBundle());
        InputStream in = ViewMaster.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(ViewMaster.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        primaryStage.getIcons().add(new Image("file:image/logo.png"));
        Scene scene = new Scene(page, 960, 530);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        return (Initializable) loader.getController();
    }

}
