/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Worker;

import java.io.File;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import notelazy.Bean.Formation;

/**
 *
 * @author sonny.lorenz
 */
public class XMLLoader {

    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     *
     * @param file
     */
    public static Formation loadFormationFromFile(File file, Formation data) {
        if (initFile(file)) {
            try {
                JAXBContext context = JAXBContext.newInstance(Formation.class);
                Unmarshaller um = context.createUnmarshaller();

                // Reading XML from the file and unmarshalling.
                data = Formation.class.cast(um.unmarshal(file));

                // Save the file path to the registry.
            } catch (Exception e) { // catches ANY exception
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Could not load data");
                alert.setContentText("Could not load data from file:\n" + file.getPath());

                alert.showAndWait();
            }
            return data;
        } else {
            return null;
        }
    }

    /**
     * Saves the current person data to the specified file.
     *
     * @param file
     */
    public static void saveFormationToFile(File file, Formation data) {
        if (initFile(file)) {
            try {
                JAXBContext context = JAXBContext
                        .newInstance(data.getClass());
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                // Wrapping our person data.
                //classType.getClass() wrapper = new Stock();
                // Marshalling and saving XML to the file.
                m.marshal(data, file);

            } catch (Exception e) { // catches ANY exception
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Could not save data");
                alert.setContentText("Could not save data to file:\n" + file.getPath());
                e.printStackTrace();
                alert.showAndWait();
            }
        }
    }

    private static boolean initFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                return false;
            }
        }
        return true;
    }
}
