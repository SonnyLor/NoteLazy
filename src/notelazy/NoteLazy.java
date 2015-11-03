/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import notelazy.View.ViewMaster;

/**
 *
 * @author sonny
 */
public class NoteLazy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ViewMaster view = new ViewMaster();
        view.launch(args);
    }
}
