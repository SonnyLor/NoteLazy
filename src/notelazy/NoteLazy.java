/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
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
    public static final String DataPath = "NoteLazy";
    public static String WorkPath;
    public static final String FormationFile = "formation." + Data_Extension;
    public static String FormationPath = DataPath + FormationFile;
    public static Formation formation = new Formation();
    public static Formation exportFormation = new Formation();
//here, we assign the name of the OS, according to Java, to a variable...
    private static String OS = (System.getProperty("os.name")).toUpperCase();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NoteLazy app = new NoteLazy();
        app.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //to determine what the workingDirectory is.
        //if it is some version of Windows
        if (OS.contains("WIN")) {
            //it is simply the location of the "AppData" folder
            WorkPath = System.getenv("AppData");
            FormationPath = WorkPath + "\\" + DataPath + "\\" + FormationFile;
        } //Otherwise, we assume Linux or Mac
        else {
            //in either case, we would start in the user's home directory
            WorkPath = System.getProperty("user.home");
            //if we are on a Mac, we are not done, we look for "Application Support"
            WorkPath += "/Library/Application Support";
            FormationPath = WorkPath + "/" + DataPath + "/" + FormationFile;
        }
        DataHandler handlerformation = new DataHandler(NoteLazy.FormationPath, true, false);
        handlerformation.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                new ViewMaster(primaryStage);
            }
        });
        handlerformation.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                new ViewMaster(primaryStage);
            }
        });
        handlerformation.start();
    }
}
