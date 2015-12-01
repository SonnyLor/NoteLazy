/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Ctrl;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import notelazy.View.ViewMaster;

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
    private RadioButton english;
    @FXML
    private RadioButton french;
    @FXML
    private RadioButton german;
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
        application.goToMainView();
    }

    public void apply() {
        application.goToMainView();
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
}
