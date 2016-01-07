/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Worker;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import notelazy.Bean.Formation;

/**
 *
 * @author sonny.lorenz
 */
public class XMLLoader {

    public static Formation loadFormationFromFile(File file) throws Exception {
        if (initFile(file)) {
            JAXBContext context = JAXBContext.newInstance(Formation.class);
            Unmarshaller um = context.createUnmarshaller();

            Formation data = (Formation) um.unmarshal(file);
            return data;
        } else {
            return null;
        }
    }

    public static void saveFormationToFile(File file, Formation data) throws Exception {
        if (initFile(file)) {
            JAXBContext context = JAXBContext
                    .newInstance(data.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, file);
        }
    }

    private static boolean initFile(File file) {
        if (!file.exists()) {
            try {
                File parent = file.getParentFile();
                if (!parent.exists()) {
                    file.mkdirs();
                }
                file.createNewFile();
            } catch (IOException ex) {
                return false;
            }
        }
        return true;
    }
}
