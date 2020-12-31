package bgu.spl.net.Operation;

import bgu.spl.net.Database;

public class LOGIN {
    private String userName;
    private String password;
    private final String message;
    private String toReturn;

    public LOGIN(String message) {
        this.message = message;
        set();
        toReturn = act();
    }

    private void set(){
        for(int i = 2 ; i < message.length() ; i++){
            if(message.charAt(i) == '0'){
                this.userName = userName;
                break;
            }
            else userName = userName + message.charAt(i);
        }
        for(int i = 3 + userName.length() ; i < message.length() - 2 ; i++){
            if(message.charAt(i) == '0'){
                this.password = password;
                break;
            }
            else password = password + message.charAt(i);
        }
    }

    private String act(){
        Database database = Database.getInstance();
        if((database.admin_Username_Password(userName , password) && !database.students_Username_Password(userName , password))) return null;
        else return "LOGIN" + userName +"0" + password;
    }

    public String toRetuen(){
        return toReturn;
    }

    public String getUserName() { return userName;}
    public String getPassword() { return password;}
}
