package Appliction.CLI;

import Appliction.Application;
import Appliction.Panel.Admin;
import Appliction.Panel.Student;

import java.util.ArrayList;
import java.util.logging.ErrorManager;

public class Cli {
    private Shell shell;
    public static String state;
    private ArrayList<String> ListOfStates = new ArrayList<>();
    public static Student curentStudent;
    public static Admin admin;
    private CommandHandler commandHandler;
    public static boolean AmIAdmin;

    public Cli() {
        shell = new Shell();
        commandHandler = new CommandHandler();
        ListOfStates.add("sign in or sign up");
        ListOfStates.add("sign in");
        ListOfStates.add("sign up");
        ListOfStates.add("adminPanel");
        ListOfStates.add("studentPanel");
        ListOfStates.add("CollegeList");
        state = "sign in or sign up";
    }

    public String processCommand(Command command) {
        String massage = commandHandler.help(command);
        if (commandHandler.SignUp(command) || commandHandler.SignIn(command) || commandHandler.si_or_SU(command) || commandHandler.seeListOfCollege(command) || commandHandler.seetheCourses(command)) {
            return massage;
        } else {
            if (massage.equals("")) {
                return Application.ERROR;
            } else {
                return massage;
            }
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
        } else if (state.equals("studentPanel")) {
            header = curentStudent.getName() + "Panel//";
        } else if (state.equals("adminPanel")) {
            header = "adminPanel//";
        } else if (state.equals("CollegeList")) {
            if (AmIAdmin) {
                header = "adminPanel//departmentList//";
            } else {
                header = curentStudent.getName() + "//departmentList//";
            }
        }
        return header;
    }
}
