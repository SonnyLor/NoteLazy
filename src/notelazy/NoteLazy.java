/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy;

import javafx.application.Application;
import javafx.stage.Stage;
import notelazy.Bean.Formation;
import notelazy.View.ViewMaster;
import notelazy.Worker.DataHandler;

/**
 *
 * @author sonny
 */
public class NoteLazy extends Application {

    public static final String Data_Extension = "xml";
    public static final String DataPath = "C:\\NoteLazy\\";
    public static final String FormationFile = "formation." + Data_Extension;
    public static final String FormationPath = DataPath + FormationFile;
    public static Formation formation = new Formation();
    public static Formation exportFormation = new Formation();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NoteLazy app = new NoteLazy();
        app.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ViewMaster(primaryStage);
        DataHandler handlerformation = new DataHandler(NoteLazy.FormationPath,true,false);
        handlerformation.start();
    }
}
