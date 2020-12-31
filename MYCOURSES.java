package bgu.spl.net.Operation;

import bgu.spl.net.Database;

import java.util.List;

public class MYCOURSES {
    private String student;
    private String toReturn;

    public MYCOURSES(String student){
        this.student = student;
        toReturn = act();
    }

    private String act(){
        Database database = Database.getInstance();
        if(!database.isStudentExsist(student)) return null;
        List<Integer> courses = database.getCoursesOfStudent(student);
        toReturn = "MYCOURSES" + "[";
        for(Integer course : courses){
            toReturn = toReturn + course + ",";
        }
        return toReturn.substring(0,toReturn.length() - 1) + "]";
    }

    public String toReturn(){
        return toReturn;
    }

}
