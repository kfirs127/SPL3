package bgu.spl.net;

import bgu.spl.net.Operation.*;
import bgu.spl.net.api.MessagingProtocol;
import com.sun.javaws.security.AppContextUtil;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class messagingProtocol implements MessagingProtocol<String> {
    private  boolean response = false;
    private boolean terminate = false;
    private String message;
    private String username;
    private String password;
    private boolean loginUser;
    private Database database;

    public messagingProtocol(){
        loginUser = false;
        database = Database.getInstance();
    }

    @Override
    public String process(String msg) {
        if (msg.substring(0, 2).equals("01")) {
            ADMINREG adminreg = new ADMINREG(msg);
            message = adminreg.toRetuen();
        }
        else if (msg.substring(0, 2).equals("02")) {
            STUDENTREG studentreg = new STUDENTREG(msg);
            message = studentreg.toRetuen();
        }
        else if (msg.substring(0, 2).equals("03")) {
            LOGIN login = new LOGIN(msg);
            message = login.toRetuen();
            if (message != null) {
                username = login.getUserName();
                password = login.getPassword();
                loginUser = true;
            }
        }
        else if (msg.substring(0, 2).equals("04")) {
            LOGOUT logout = new LOGOUT();
            message = "LOGOUT";
            terminate = true;
            loginUser = false;
        }
        else if (msg.substring(0, 2).equals("05")) {
            if (database.isAdminExsist(username) && loginUser) {
                COURSEREG coursereg = new COURSEREG(msg.substring(2), username);
                message = coursereg.toReturn();
            }
            //error message;
        }
        else if (msg.substring(0, 2).equals("06")) {
            if (loginUser) {
                KDAMCHECK kdamcheck = new KDAMCHECK(msg.substring(2));
                message = kdamcheck.toReturn();
            }
            //error message;
        }
        else if (msg.substring(0, 2).equals("07")) {
            if (loginUser) {
                COURSESTAT coursestat = new COURSESTAT(msg.substring(2), username);
                message = coursestat.toReturn();
            }
            //error message;
        }
        else if (msg.substring(0, 2).equals("08")) {
            if (loginUser) {
                STUDENTSTAT studentstat = new STUDENTSTAT(username);
                message = studentstat.toReturn();
            }
            //error message;
        }
        else if (msg.substring(0, 2).equals("09")) {
            if (loginUser) {
                ISREGISTERED isregistered = new ISREGISTERED(username, msg.substring(2));
                message = isregistered.toReturn();
            }
            //error message;
        }
        else if (msg.substring(0, 2).equals("10")) {
            if (loginUser) {
                UNREGISTER unregister = new UNREGISTER(msg.substring(2) , username);
                message = unregister.toReturn();
            }
            //error message;
        }
        else if (msg.substring(0, 2).equals("11")) {
            if (loginUser) {
                MYCOURSES mycourses = new MYCOURSES(username);
                message = mycourses.toReturn();
            }
        }
        // need to add ack message and error message
    }

    @Override
    public boolean shouldTerminate() {
        return terminate;
    }

    public void setResponse(boolean response) { this.response = response; }
}
