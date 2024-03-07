package Appliction.CLI;

import Appliction.Application;
import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.courses;
import Appliction.Panel.Student;

import java.util.ArrayList;

public class Cli {
    public static String state;
    private ArrayList<String> ListOfStates = new ArrayList<>();
    public static Student curentStudent;
    private CommandHandler commandHandler;
    public static boolean AmIAdmin;
    public static College curentCollege;
    public static courses curentCourse;

    public Cli() {
        commandHandler = new CommandHandler();
        ListOfStates.add("sign in or sign up");
        ListOfStates.add("sign in");
        ListOfStates.add("sign up");
        ListOfStates.add("adminPanel");
        ListOfStates.add("studentPanel");
        ListOfStates.add("CollegeList");
        ListOfStates.add("coursesList");
        ListOfStates.add("studentCourseList");
        state = "sign in or sign up";
    }

    public String processCommand(Command command) {
        boolean dihave = commandHandler.help(command);
        if (dihave) {
            return "";
        }
        if (commandHandler.backChecker(command) || commandHandler.SignUp(command) || commandHandler.SignIn(command) ||
                commandHandler.si_or_SU(command) || commandHandler.seeListOfCollege(command) ||
                commandHandler.seetheCourses(command) || commandHandler.addCourse(command) ||
                commandHandler.addStudentCourse(command) || commandHandler.seetheStudent(command) ||
                commandHandler.deleteStudent(command) || commandHandler.creatCollege(command) ||
                commandHandler.increaseCapacityCourse(command) || commandHandler.StudentSeeOwnPikedCourse(command) ||
                commandHandler.DeleteOwnCoursePiked(command) || commandHandler.StudentPikeCourse(command) ||
                commandHandler.Importt(command) || commandHandler.Export(command)||commandHandler.exportf(command)
        ) {
            return "";
        } else {
            return Application.ERROR + "\n";
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
            header = curentStudent.getUserName() + "Panel//";
        } else if (state.equals("adminPanel")) {
            header = "adminPanel//";
        } else if (state.equals("CollegeList")) {
            if (AmIAdmin) {
                header = "adminPanel//departmentList//";
            } else {
                header = curentStudent.getUserName() + "//departmentList//";
            }
        } else if (state.equals("coursesList")) {
            header = "adminPanel//departmentList//" + curentCollege.getName() + "//";
        } else if (state.equals("studentCourseList")) {
            header = "adminPanel//departmentList//" + curentCollege.getName() + "//" + curentCourse.getName() + "//";
        } else if (state.equals("ownCourse")) {
            header = curentStudent.getUserName() + "Panel//" + "Picked course//";
        }
        return header;
    }
}
