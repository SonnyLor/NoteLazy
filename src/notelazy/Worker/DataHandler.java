/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Worker;

import java.io.File;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import notelazy.NoteLazy;

/**
 *
 * @author sonny
 */
public class DataHandler extends Service<Void> {

    public String path;
    public boolean read;
    public boolean export;

    public DataHandler(String path, boolean read, boolean export) {
        this.path = path;
        this.read = read;
        this.export = export;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {

            @Override
            protected Void call() {
                try {
                    if (export) {
                            XMLLoader.saveFormationToFile(new File(path), NoteLazy.exportFormation);
                    } else {
                        if (read) {
                            NoteLazy.formation = XMLLoader.loadFormationFromFile(new File(path));
                        } else {
                            XMLLoader.saveFormationToFile(new File(path), NoteLazy.formation);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }
}
