package bgu.spl.net.Operation;

import bgu.spl.net.Database;

public class ISREGISTERED {
    private final String userName;
    private final int courseNum;
    private final String toReturn;

    public ISREGISTERED(String userName , String courseNum){
        this.userName = userName;
        this.courseNum = Integer.parseInt(courseNum);
        toReturn = act();
    }

    private String act(){
        Database database = Database.getInstance();
        if(database.isStudentRegisterToCourse(userName , courseNum)) return "REGISTERED";
        return "NOT REGISTERED";
    }

    public String toReturn(){
        return toReturn;
    }
}
