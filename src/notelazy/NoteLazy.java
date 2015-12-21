/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy;

import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import notelazy.Bean.Formation;
import notelazy.Bean.Student;
import notelazy.View.ViewMaster;
import notelazy.Worker.DataHandler;
import notelazy.Worker.XMLLoader;

/**
 *
 * @author sonny
 */
public class NoteLazy extends Application {

    public static final String DataPath = "C:\\NoteLazy\\";
    public static final String FormationFile = "formation.xml";
    public static final String FormationPath = DataPath + FormationFile;
    public static final String StudentFile = "student.xml";
    public static final String StudentPath = DataPath + StudentFile;
    public static DataHandler handler;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NoteLazy app = new NoteLazy();
        app.launch(args);
        NoteLazy.handler = new DataHandler();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewMaster view = new ViewMaster(primaryStage);
    }
}
