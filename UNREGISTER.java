package bgu.spl.net.Operation;

import bgu.spl.net.Database;

public class UNREGISTER {
    private final int courseNum;
    private final String student;
    private String toReturn;

    public UNREGISTER(String courseNum , String student) {
        this.courseNum = Integer.parseInt(courseNum);
        this.student = student;
        toReturn = act();
    }

    private String act(){
        Database database = Database.getInstance();
        if(!database.isStudentExsist(student)) return null;
        if(!database.exsistingCours(courseNum)) return null;
        if(!database.isStudentRegisterToCourse(student , courseNum)) return null;
        database.unregisterStudentFromCourse(student , courseNum);
        return "UNREGISTER" + courseNum;
    }

    public String toReturn() {
        return toReturn;
    }
}
