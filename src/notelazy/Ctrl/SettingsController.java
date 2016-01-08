/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Ctrl;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.RangeSlider;
import notelazy.View.ViewMaster;
import notelazy.NoteLazy;

/**
 * FXML Controller class
 *
 * @author sonny
 */
public class SettingsController implements Initializable {

    private ViewMaster application;
    private Locale returnLocale;
    private ResourceBundle rb;
    @FXML
    private GridPane gridPane;
    @FXML
    private RadioButton english;
    @FXML
    private RadioButton french;
    @FXML
    private RadioButton german;
    private RangeSlider range;
    private TextField minField;
    private TextField maxField;
    private int returnMax;
    private int returnMin;
    private static final Locale localeGerman = new Locale("de", "DE");
    private static final Locale localeFrench = new Locale("fr", "CH");
    private static final Locale localeEnglish = new Locale("en", "EN");

    public void setApp(ViewMaster application) {
        this.application = application;
        application.setTitle(rb.getString("title"), rb.getString("title.settings"));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        returnMin = NoteLazy.formation.getMin();
        returnMax = NoteLazy.formation.getMax();
        GridPane griPaneSlider = new GridPane();
        range = new RangeSlider();
        griPaneSlider.add(range, 0,0, 2, 1);
        range.setMax(100);
        range.setMin(0);
        range.setMajorTickUnit(10);
        range.setBlockIncrement(1);
        range.setShowTickLabels(true);
        range.setShowTickMarks(true);
        range.lowValueProperty().bindBidirectional(NoteLazy.formation.getMinProp());
        range.highValueProperty().bindBidirectional(NoteLazy.formation.getMaxProp());
        minField = new TextField();
        maxField = new TextField();
        minField.setEditable(false);
        maxField.setEditable(false);
        maxField.setDisable(true);
        minField.setDisable(true);
        minField.textProperty().bind( range.lowValueProperty().asString("%.0f"));
        maxField.textProperty().bind( range.highValueProperty().asString("%.0f"));
        griPaneSlider.add(minField, 0, 1);
        griPaneSlider.add(maxField, 1, 1);
        gridPane.add(griPaneSlider, 1, 1);
        returnLocale = rb.getLocale();
        if (returnLocale.equals(localeEnglish)) {
            english.setSelected(true);
        }
        if (returnLocale.equals(localeFrench)) {
            french.setSelected(true);
        }
        if (returnLocale.equals(localeGerman)) {
            german.setSelected(true);
        }
        this.rb = rb;
    }

    public void cancel() {
        application.setLocale(returnLocale);
        NoteLazy.formation.setMax(returnMax);
        NoteLazy.formation.setMin(returnMin);
        application.goToMainView();
    }

    public void apply() {
        application.goToMainView();
        application.saveFormation();
    }

    public void english() {
        application.setLocale(localeEnglish);
        english.setSelected(true);
    }

    public void french() {
        application.setLocale(localeFrench);
        french.setSelected(true);
    }

    public void deutch() {
        application.setLocale(localeGerman);
        german.setSelected(true);
    }

    public class DisplayedPref {

        private SimpleIntegerProperty min;
        private SimpleIntegerProperty max;
        private Locale local;

        public DisplayedPref(int min, int max, Locale local) {
            this.min = new SimpleIntegerProperty(min);
            this.max = new SimpleIntegerProperty(max);
            this.local = local;
        }

        public int getMin() {
            return min.get();
        }

        public int getMax() {
            return max.get();
        }

        public Locale getLocal() {
            return local;
        }

    }
}
