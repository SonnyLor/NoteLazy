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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;
import notelazy.Bean.Bloc;
import notelazy.Bean.Lesson;
import notelazy.NoteLazy;
import notelazy.View.ViewMaster;

/**
 * FXML Controller class
 *
 * @author sonny
 */
public class LessonsController implements Initializable {

    @FXML
    private TextField lesson;
    @FXML
    private TextField weight;
    @FXML
    private ComboBox<Bloc> blocsChoice;
    @FXML
    private TableView<DisplayableLesson> table;
    @FXML
    private TableColumn colWeight;
    @FXML
    private TableColumn colBloc;
    @FXML
    private TableColumn colLesson;

    private ViewMaster application;
    private ResourceBundle rb;
    private Service<Void> formationLoader;
    private Bloc selectedBloc;
    private final Lesson newLesson = new Lesson();

    public void setApp(ViewMaster application) {
        this.application = application;
        application.setTitle(rb.getString("title"), rb.getString("title.edition.lesson"));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        lesson.textProperty().bindBidirectional(newLesson.getNameProp());
        weight.textProperty().bindBidirectional(newLesson.getWeightProp(), new NumberStringConverter());
        ArrayList<Bloc> blocs = NoteLazy.formation.blocs;
        blocsChoice.getItems().addAll(blocs);
        blocsChoice.setEditable(true);
        blocsChoice.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                selectedBloc = new Bloc(newValue);
            }
        });
        colBloc.setCellValueFactory(new PropertyValueFactory<DisplayableLesson, String>("bloc"));
        colLesson.setCellValueFactory(new PropertyValueFactory<DisplayableLesson, String>("name"));
        colWeight.setCellValueFactory(new PropertyValueFactory<DisplayableLesson, String>("weight"));
        for (Bloc bloc : blocs) {
            for (Lesson lesson : bloc.lessons) {
                table.getItems().add(new DisplayableLesson(lesson.getNameProp(), bloc.getNameProp(), lesson.getWeightProp()));
            }
        }
    }

    public void add() {
        int index = NoteLazy.formation.blocs.indexOf(selectedBloc);
        if (index > -1) {
            Bloc bloc = NoteLazy.formation.blocs.get(index);
            bloc.lessons.add(newLesson);
            table.getItems().add(new DisplayableLesson(newLesson.getNameProp(), bloc.getNameProp(), newLesson.getWeightProp()));
        } else {
            Bloc bloc = selectedBloc;
            NoteLazy.formation.addBloc(bloc);
            bloc.lessons.add(newLesson);
            blocsChoice.getItems().add(bloc);
            table.getItems().add(new DisplayableLesson(newLesson.getNameProp(), bloc.getNameProp(), newLesson.getWeightProp()));
        }
        application.saveFormation();
    }

    public void selectBloc() {
        SingleSelectionModel<Bloc> bloc = blocsChoice.getSelectionModel();
        selectedBloc = bloc.getSelectedItem();
    }

    public void deleteSelected() {
        DisplayableLesson selected = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(selected);
        Bloc removeBloc = new Bloc();
        for (Bloc bloc : NoteLazy.formation.blocs) {
            if (bloc.getName().equals(selected.getBloc())) {
                removeBloc = bloc;
                Lesson removeLesson = new Lesson();
                for (Lesson curretnLesson : bloc.lessons) {
                    if (curretnLesson.getName().equals(selected.getName()) && curretnLesson.getWeight() == selected.getWeight()) {
                        removeLesson = curretnLesson;
                        break;
                    }
                }
                bloc.removeLesson(removeLesson);
            }
        }
        if (removeBloc.lessons.isEmpty()) {
            NoteLazy.formation.removeBloc(removeBloc);
        }
        application.saveFormation();
    }

    public void back() {
        application.saveFormation();
        application.goToMainView();
    }

    public class DisplayableLesson {

        private final SimpleStringProperty name;
        private final SimpleStringProperty bloc;
        private final SimpleDoubleProperty weight;

        public DisplayableLesson(SimpleStringProperty name, SimpleStringProperty bloc, SimpleDoubleProperty weight) {
            this.name = name;
            this.bloc = bloc;
            this.weight = weight;
        }

        public String getName() {
            return name.get();
        }

        public String getBloc() {
            return bloc.get();
        }

        public Double getWeight() {
            return weight.get();
        }

        public void bindNameTo(SimpleStringProperty name) {
            this.name.bind(name);
        }

        public void bindLessonTo(SimpleStringProperty bloc) {
            this.bloc.bind(bloc);
        }

        public void bindWeightTo(SimpleDoubleProperty weight) {
            this.weight.bind(weight);
        }
    }
}
