package bgu.spl.net.Operation;

import bgu.spl.net.Database;
import bgu.spl.net.messagingProtocol;

public class COURSEREG extends messagingProtocol {
    private final String username;
    private final int courseNum;
    private final String toReturn;

    public COURSEREG(String courseNum , String username){
        this.username = username;
        this.courseNum = Integer.parseInt(courseNum);
        toReturn = act();
    }

    public String act(){
        Database database = Database.getInstance();
        if(!database.isAdminExsist(username)) return null;
        else if(database.exsistingCours(courseNum)) return null;
        else if(!database.availableSeats(courseNum)) return null;
        else if(!database.allKdamCourse(username , courseNum)) return null;
        else{
            database.registerCourse(courseNum , username);
            return  "COURSEREG" + courseNum;
        }
    }

    public String toReturn(){ return toReturn; }

}
