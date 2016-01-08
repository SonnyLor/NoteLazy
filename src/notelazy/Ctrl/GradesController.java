/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Ctrl;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;
import notelazy.Bean.Bloc;
import notelazy.Bean.Lesson;
import notelazy.Bean.Note;
import notelazy.NoteLazy;
import notelazy.View.ViewMaster;

/**
 * FXML Controller class
 *
 * @author sonny
 */
public class GradesController implements Initializable {

    private ViewMaster application;
    private ResourceBundle rb;

    @FXML
    private TextField grade;
    @FXML
    private TextField weight;
    @FXML
    private ComboBox<Lesson> lessonsChoice;
    @FXML
    private TableView<DisplayableGrade> table;
    @FXML
    private TableColumn colGrade;
    @FXML
    private TableColumn colWeight;
    @FXML
    private TableColumn colLesson;
    @FXML
    private TableColumn colBloc;

    Note newNote = new Note(1, 1);
    Lesson lessonChoice;
    Bloc lessonBlocChoice;

    public void setApp(ViewMaster application) {
        this.application = application;
        application.setTitle(rb.getString("title"), rb.getString("title.edition.grade"));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        grade.setOnKeyReleased(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                String contenu = grade.getText();
                if (!contenu.isEmpty()) {
                    System.out.println(contenu);
                    try {
                        double value = Double.valueOf(contenu);
                        if (value > NoteLazy.formation.getMax()) {
                            grade.setText(Integer.toString(NoteLazy.formation.getMax()));
                            grade.selectAll();
                        } else if (value < NoteLazy.formation.getMin()) {
                            grade.setText(Integer.toString(NoteLazy.formation.getMin()));
                            grade.selectAll();
                        }

                    } catch (Exception e) {
                        grade.setText(Integer.toString(NoteLazy.formation.getMax()));
                    }
                }
            }
        });
        grade.textProperty().bindBidirectional(newNote.getNoteProp(), new NumberStringConverter());
        weight.textProperty().bindBidirectional(newNote.getWeightProp(), new NumberStringConverter());
        ArrayList<Bloc> blocs = NoteLazy.formation.blocs;
        for (Bloc bloc : blocs) {
            lessonsChoice.getItems().addAll(bloc.lessons);
            if (bloc.lessons != null) {
                for (Lesson lesson : bloc.lessons) {
                    if (lesson.notes != null) {
                        for (Note note : lesson.notes) {
                            table.getItems().add(new DisplayableGrade(note.getNoteProp(), lesson.getNameProp(), note.getWeightProp(), bloc.getNameProp()));
                        }
                    }

                }
            }
        }
        colGrade.setCellValueFactory(new PropertyValueFactory<DisplayableGrade, String>("grade"));
        colLesson.setCellValueFactory(new PropertyValueFactory<DisplayableGrade, String>("lesson"));
        colWeight.setCellValueFactory(new PropertyValueFactory<DisplayableGrade, String>("weight"));
        colBloc.setCellValueFactory(new PropertyValueFactory<DisplayableGrade, String>("bloc"));

    }

    public void selectLesson() {
        new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                lessonChoice = lessonsChoice.getSelectionModel().getSelectedItem();
                ArrayList<Bloc> blocs = NoteLazy.formation.blocs;
                for (Bloc bloc : blocs) {
                    if (bloc.lessons.contains(lessonChoice)) {
                        lessonBlocChoice = bloc;
                        break;
                    }
                }
                return null;
            }
        }.run();
    }

    public void back() {
        application.goToMainView();
    }

    public void addGrade() {
        lessonChoice.notes.add(newNote);
        table.getItems().add(new DisplayableGrade(newNote.getNoteProp(), lessonChoice.getNameProp(), newNote.getWeightProp(), lessonBlocChoice.getNameProp()));
        application.saveFormation();
    }

    public void deleteGrade() {
        new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                DisplayableGrade selectedGrade = table.getSelectionModel().getSelectedItem();
                table.getItems().remove(selectedGrade);
                Note removeNote = new Note();
                for (Bloc bloc : NoteLazy.formation.blocs) {
                    if (bloc.getName().equals(selectedGrade.getBloc())) {
                        for (Lesson curretnLesson : bloc.lessons) {
                            if (curretnLesson.getName().equals(selectedGrade.getLesson())) {
                                for (Note note : curretnLesson.notes) {
                                    if (note.getNote() == selectedGrade.getGrade() && selectedGrade.getWeight() == note.getWeight()) {
                                        removeNote = note;
                                        break;
                                    }
                                }
                                curretnLesson.notes.remove(removeNote);
                            }
                        }
                    }
                }
                application.saveFormation();
                return null;
            }
        }.run();
    }

    public class DisplayableGrade {

        private final SimpleDoubleProperty grade;
        private final SimpleStringProperty lesson;
        private final SimpleStringProperty bloc;
        private final SimpleDoubleProperty weight;

        public DisplayableGrade(SimpleDoubleProperty grade, SimpleStringProperty lesson,
                SimpleDoubleProperty weight, SimpleStringProperty bloc) {
            this.grade = grade;
            this.lesson = lesson;
            this.weight = weight;
            this.bloc = bloc;
        }

        public Double getGrade() {
            return grade.get();
        }

        public String getLesson() {
            return lesson.get();
        }

        public Double getWeight() {
            return weight.get();
        }

        public String getBloc() {
            return bloc.get();
        }
    }
}
