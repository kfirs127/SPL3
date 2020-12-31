package bgu.spl.net.Operation;

import bgu.spl.net.Database;

import java.util.List;

public class KDAMCHECK {
    private final int courseNum;
    private String toRetuen;

    public KDAMCHECK(String message){
        courseNum = Integer.parseInt(message);
        toRetuen = act();
    }

    private String act(){
        Database database = Database.getInstance();
        if(!database.exsistingCours(courseNum)) return null;
        List<Integer> kdam = database.getKDAMCourse(courseNum);
        toRetuen = "KDAMCHECK";
        for(Integer i : kdam){
            toRetuen = toRetuen + i + ",";
        }
        return toRetuen;
    }

    public String toReturn() { return toRetuen; }
}
