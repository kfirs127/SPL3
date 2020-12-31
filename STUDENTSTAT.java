package bgu.spl.net.Operation;

import bgu.spl.net.Database;
import com.sun.jmx.snmp.SnmpOidDatabaseSupport;

public class STUDENTSTAT {

    private String student;
    private String toReturn;

    public STUDENTSTAT(String student){
        this.student = student;
        toReturn = act();
    }

    private String act(){
        Database database = Database.getInstance();
        if(!database.isStudentExsist(student)) return null;
        else{
            return "Student: " + student + "\n" + "Courses:" + database.getCoursesOfStudent(student);
        }
    }

    public String toReturn() { return toReturn; }
}
