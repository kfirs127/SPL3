package bgu.spl.net.Operation;

import bgu.spl.net.Database;

public class COURSESTAT {
    private String userName;
    private int courseNum;
    private final String toReturn;

    public COURSESTAT(String courseNum , String userName){
        this.courseNum = Integer.parseInt(courseNum);
        this.userName = userName;
        toReturn = act();
    }

    private String act(){
        Database database = Database.getInstance();
        if(!database.isAdminExsist(userName)) return null;
        else if(!database.exsistingCours(courseNum)) return null;
        else{
            return "COURSESTAT" + "<" + "\n"
                    + "Course: " + "(" + courseNum + "(" + database.getCourseName(courseNum) + ")" + "\n"
                    + "Seats Available: " + database.getAvailableSeats(courseNum) + "/" + database.getMaxSeats(courseNum) + "\n"
                    + "Students Registered: " + database.getStuentsInCourse(courseNum) + ">";
        }
    }

    public String toReturn(){
        return toReturn;
    }
}
