package Appliction.CLI;

import Appliction.Application;
import java.util.ArrayList;

public class Cli {
    private Shell shell;
    public static String state;
    private ArrayList<String> ListOfStates = new ArrayList<>();
    public Cli() {
        shell = new Shell();
        ListOfStates.add("sign in or sign up");
        ListOfStates.add("sign in");
        state = "sign in or sign up";
    }

    public String processCommand(Command command) {
        return Application.Error;
    }


    public String getHeader() {
        String header = "";
        if (state.equals("sign in or sign up")) {
            header = "Sign in or sign up//";
        }else if (state.equals("sign in")){
            header = "sign in(enter your username and password(with 1 space between them)//";
        }
//        Directory working = shell.getWorkingDirectory();
//        String first = user + "@" + computerName + ":~";
//        ArrayList<String> nasl = new ArrayList<>();
//        nasl =working.getDirectory();
//        for (int i = nasl.size()-1; i >= 0 ; i=i-1) {
//            if (i==nasl.size()-1&&nasl.get(nasl.size()-1).equals("~")){
//                continue;
//            }
//            first = first + "/"+nasl.get(i);
//        }
//        first = first + "$";
//        return first;
        return header;
    }
}
