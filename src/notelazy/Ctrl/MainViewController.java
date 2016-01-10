/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Ctrl;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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
    private BorderPane borderPane;
    @FXML
    private TableView<DisplayedAverage> table;
    @FXML
    private TableColumn colAverage;
    @FXML
    private TableColumn colBloc;
    private LineChart<String, Double> lineChart;
    private BarChart<String, Double> barChart;
    private StackPane stackpane;

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
        final CategoryAxis xAxis1 = new CategoryAxis();
        lineChart = new LineChart(xAxis1, createYaxis());
        barChart = new BarChart(xAxis1, createYaxis());
        setDefaultChartProperties((XYChart) lineChart);
        setDefaultChartProperties((XYChart) barChart);
        colBloc.setCellValueFactory(new PropertyValueFactory<DisplayedAverage, String>("bloc"));
        colAverage.setCellValueFactory(new PropertyValueFactory<DisplayedAverage, String>("average"));
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                displayBloc();
            }
        });
        NoteLazy.formation.blocs.stream().forEach((bloc) -> {
            double sum = 0;
            int max = NoteLazy.formation.getMax();
            int min = NoteLazy.formation.getMin();
            double totalWeight = 0;
            for (Lesson lesson : bloc.lessons) {
                if (!lesson.notes.isEmpty()) {
                    double sumNote = 0;
                    double totalWeightNote = 0;
                    for (Note note : lesson.notes) {
                        if (note.getNote()>max)note.setNote(max);
                        else if (note.getNote()<min)note.setNote(min);
                        sumNote += (note.getNote() * note.getWeight());
                        totalWeightNote += note.getWeight();

                    }
                    sum += (sumNote / totalWeightNote) * lesson.getWeight();
                    totalWeight += lesson.getWeight();
                }
            }
            double average = sum / totalWeight;
            table.getItems().add(new DisplayedAverage(Double.isNaN(average) ? 0 : average, bloc.getName()));
        });
    }

    private void setDefaultChartProperties(XYChart<String, Number> chart) {
        chart.setLegendVisible(false);
        chart.setAnimated(false);
    }

    private StackPane layerCharts(final XYChart<String, Number>... charts) {
        for (int i = 1; i < charts.length; i++) {
            configureOverlayChart(charts[i]);
        }

        StackPane stackpane = new StackPane();
        stackpane.getChildren().addAll(charts);

        return stackpane;
    }

    private void configureOverlayChart(final XYChart<String, Number> chart) {
        chart.setAlternativeRowFillVisible(false);
        chart.setAlternativeColumnFillVisible(false);
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalGridLinesVisible(false);
        chart.getXAxis().setVisible(false);
        chart.getYAxis().setVisible(false);
        chart.getStylesheets().addAll(getClass().getResource("/notelazy/ressources/overlay-chart.css").toExternalForm());
    }

    private NumberAxis createYaxis() {
        final NumberAxis axis = new NumberAxis(NoteLazy.formation.getMin(), NoteLazy.formation.getMax() + 1, 1);
        axis.setPrefWidth(35);
        axis.setMinorTickCount(10);

        axis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(axis) {
            @Override
            public String toString(Number object) {
                return String.format("%7.2f", object.floatValue());
            }
        });

        return axis;
    }

    public void importData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(rb.getString("content.open"));
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter(NoteLazy.Data_Extension.toUpperCase(application.getLocale()) + " "+rb.getString("content.files"), "*." + NoteLazy.Data_Extension),
                new ExtensionFilter(rb.getString("content.allFiles"), "*.*"));
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
            load.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    application.goToMainView();
                }
            });
            load.start();
        }
    }

    public void exportData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(rb.getString("content.save"));
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter(NoteLazy.Data_Extension.toUpperCase(application.getLocale()) + " "+rb.getString("content.files"), "*." + NoteLazy.Data_Extension),
                new ExtensionFilter(rb.getString("content.allFiles"), "*.*"));
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
        System.exit(0);
    }

    public void displayBloc() {
        DisplayedAverage selectedBloc = table.getSelectionModel().getSelectedItem();
        lineChart.setTitle(selectedBloc.getBloc());
        barChart.setTitle(selectedBloc.getBloc());
        lineChart.getData().clear();
        barChart.getData().clear();
        new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                double sum = 0;
                double totalWeight = 0;
                String nameAxY =" "+ rb.getString("content.grade")+ " ";
                for (Bloc bloc : NoteLazy.formation.blocs) {
                    if (bloc.getName().equals(selectedBloc.getBloc())) {

                        XYChart.Series seriesM = new XYChart.Series();
                        seriesM.setName(rb.getString("content.average"));
                        for (Lesson lesson : bloc.lessons) {
                            int i = 0;
                            if (!lesson.notes.isEmpty()) {
                                double sumNote = 0;
                                double totalWeightNote = 0;
                                for (Note note : lesson.notes) {
                                    sumNote += (note.getNote() * note.getWeight());
                                    totalWeightNote += note.getWeight();
                                    XYChart.Series series = new XYChart.Series();
                                    double currentAverage = sumNote/totalWeightNote;
                                    series.getData().add(new XYChart.Data(lesson.getName()+nameAxY+i, note.getNote()));
                                    Platform.runLater(() -> barChart.getData().add(series));
                                    double globalAverage = (currentAverage*lesson.getWeight()+sum)/(lesson.getWeight()+totalWeight);
                                    seriesM.getData().add(new XYChart.Data(lesson.getName()+nameAxY+i++, globalAverage));
                                }
                                double average = (sumNote / totalWeightNote);
                                sum += average * lesson.getWeight();
                                totalWeight += lesson.getWeight();
                            }
                        }

                        Platform.runLater(() -> lineChart.getData().add(seriesM));
                        break;
                    }
                }
                Platform.runLater(()
                        -> borderPane.setRight(layerCharts((XYChart) barChart, (XYChart) lineChart)));
                return null;
            }
        }.run();
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

    public class DisplayedAverage {

        private final SimpleDoubleProperty average = new SimpleDoubleProperty();
        private final SimpleStringProperty bloc = new SimpleStringProperty();

        public DisplayedAverage(double average, String bloc) {
            this.average.set(average);
            this.bloc.set(bloc);
        }

        public Double getAverage() {
            return average.get();
        }

        public String getBloc() {
            return bloc.get();
        }
    }
}
