/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notelazy.Worker;

import java.io.File;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import notelazy.Bean.Formation;
import notelazy.Bean.Student;
import notelazy.NoteLazy;

/**
 *
 * @author sonny
 */
public class DataHandler extends Service<Student>{

    public Student student;
    public Formation formation;
    
    public DataHandler(){    
        this.formation = new Formation();
        this.student = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    @Override
    protected Task<Student> createTask() {
        /*this.formation = new Formation();
        XMLLoader.loadFormationFromFile(new File(NoteLazy.FormationPath), formation);
        */
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return new Task<Student>() {
            @Override
            protected Student call() throws Exception {
                Student student = new Student();
                XMLLoader.loadStudentFromFile(new File(NoteLazy.StudentPath), student);
                return student;
            }
        };
    }
}
    
