package Appliction.CLI;

import Appliction.Application;

import java.util.ArrayList;

public class Cli {
    private Shell shell;
    public static String state;
    private ArrayList<String> ListOfStates = new ArrayList<>();
    private CommandHandler commandHandler;

    public Cli() {
        shell = new Shell();
        commandHandler = new CommandHandler();
        ListOfStates.add("sign in or sign up");
        ListOfStates.add("sign in");
        ListOfStates.add("sign up");
        ListOfStates.add("adminPanel");
        ListOfStates.add("studentPanel");
        state = "sign in or sign up";
    }

    public String processCommand(Command command) {
        String massage = commandHandler.help(command);

        if (commandHandler.SignUp(command) || commandHandler.SignIn(command) || commandHandler.si_or_SU(command)) {
            return massage;
        }
        if (massage.equals("")) {
            return Application.ERROR;
        } else {
            return massage;
        }
    }


    public String getHeader() {
        String header = "";
        if (state.equals("sign in or sign up")) {
            header = "Signin or signup(stuck together(in and up)//";
        } else if (state.equals("sign in")) {
            header = "sign in(enter your username and password(with 1 space between them)//";
        } else if (state.equals("sign up")) {
            header = "sign up(enter your username and password(with 1 space between them)//";
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
//        return first
        return header;
    }
}
