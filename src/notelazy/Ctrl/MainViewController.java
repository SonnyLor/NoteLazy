/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Ctrl;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import notelazy.Bean.Bloc;
import notelazy.Bean.Lesson;
import notelazy.Bean.Note;
import notelazy.NoteLazy;
import notelazy.View.ViewMaster;
import notelazy.Worker.DataHandler;

/**
 * FXML Controller class
 *
 * @author sonny
 */
public class MainViewController implements Initializable {

    private ViewMaster application;
    private ResourceBundle rb;

    @FXML
    private TableView<displayedAverage> table;
    @FXML
    private TableColumn colAverage;
    @FXML
    private TableColumn colBloc;

    public void setApp(ViewMaster application) {
        this.application = application;
        application.setTitle(rb.getString("title"), rb.getString("title.main"));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;

        colBloc.setCellValueFactory(new PropertyValueFactory<displayedAverage, String>("bloc"));
        colAverage.setCellValueFactory(new PropertyValueFactory<displayedAverage, String>("average"));
        new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                for (Bloc bloc : NoteLazy.formation.blocs) {
                    double sum = 0;
                    double totalWeight = 1;
                    for (Lesson lesson : bloc.lessons) {
                        double sumNote = 0;
                        double totalWeightNote = 1;
                        for (Note note : lesson.notes) {
                            sumNote += (note.getNote() * note.getWeight());
                            totalWeightNote += note.getWeight();

                        }
                        System.out.println(lesson.getName() + " moyenne " + sum / totalWeight);
                        sum += (sumNote / totalWeightNote) * lesson.getWeight();
                        totalWeight += lesson.getWeight();
                    }
                    System.out.println(bloc.getName() + " moyenne " + sum / totalWeight);
                    table.getItems().add(new displayedAverage(sum / totalWeight, bloc.getName()));
                }
                return null;
            }
        }.run();
    }

    public void importData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter(NoteLazy.Data_Extension.toUpperCase(application.getLocale()) + " Files", "*." + NoteLazy.Data_Extension),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(this.application.getPrimaryStage());
        if (selectedFile != null) {
            DataHandler load = new DataHandler(selectedFile.getPath(), true, false);
            load.setOnFailed(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    application.setErrorMEssage(rb.getString("error.title"),
                            rb.getString("error.header.loadXML"), rb.getString("error.message.load.file") + selectedFile
                            + event.getSource().getMessage());
                }
            });
            load.start();
        }
    }

    public void exportData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter(NoteLazy.Data_Extension.toUpperCase(application.getLocale()) + " Files", "*." + NoteLazy.Data_Extension),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showSaveDialog(this.application.getPrimaryStage());
        if (selectedFile != null) {
            new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    NoteLazy.exportFormation = NoteLazy.formation;
                    for (Bloc bloc : NoteLazy.exportFormation.blocs) {
                        for (Lesson lesson : bloc.lessons) {
                            lesson.notes = null;
                        }
                    }
                    DataHandler load = new DataHandler(selectedFile.getPath(), false, true);
                    load.setOnFailed(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent event) {
                            application.setErrorMEssage(rb.getString("error.title"),
                                    rb.getString("error.header.loadXML"), rb.getString("error.message.load.file") + selectedFile
                                    + event.getSource().getMessage());
                        }
                    });
                    load.start();
                    return null;
                }
            }.run();
        }
    }

    public void settings() {
        application.goToSettingsView();
    }

    public void close() {

    }

    public void editLessons() {
        application.goToEditLessonView();
    }

    public void editGrade() {
        application.goToEditGradesView();
    }

    public void help() {
        application.goToHelpView();
    }

    public class displayedAverage {

        private final SimpleDoubleProperty average = new SimpleDoubleProperty();
        private final SimpleStringProperty bloc = new SimpleStringProperty();

        public displayedAverage(double average, String bloc) {
            this.average.set(average);
            this.bloc.set(bloc);
        }

        public Double getGrade() {
            return average.get();
        }

        public String getLesson() {
            return bloc.get();
        }
    }
}
