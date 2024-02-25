package Appliction.CLI;

import Appliction.Application;
import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.courses;
import UserNamePassword.SignIn_UpHandler;
import Appliction.Panel.university;

public class CommandHandler {
    SignIn_UpHandler signInUpHandler = new SignIn_UpHandler();

    public String help(Command command) {
        String HelpResult = "";
        if (command.getCommand().equals("help") && command.getArg() == null) {
            if (Cli.state.equals("sign in or sign up")) {
                HelpResult = "signin -> enter to your account\nsignup -> creat an account";
            } else if (Cli.state.equals("sign in")) {
                HelpResult = "back -> back to the sign in or sign up state\nenter a user name and password of your account";
            } else if (Cli.state.equals("sign up")) {
                HelpResult = "back -> back to the sign in or sign up state\nenter a user name and password that you want for your account";
            } else if (Cli.state.equals("adminPanel")) {
                HelpResult = "back -> back to the sign in state\nld -> for see the list of the Department\n";//todo
            } else if (Cli.state.equals("studentPanel")) {
                HelpResult = "back -> back to the sign in\nld -> for see the list of the Department\n";//todo
            } else if (Cli.state.equals("CollegeList")) {
                HelpResult = "back -> back to the Panel state\nname any department -> see the department courses";
            }
        }
        return HelpResult;
    }

    public boolean SignUp(Command command) {
        if (Cli.state.equals("sign up")) {
            if (command.getArg() == null && command.getCommand().equals("back")) {
                Cli.state = "sign in or sign up";
                return true;
            }
            if (signInUpHandler.DoWeHaveThis(command.getCommand())) {
                System.out.println("The username is repeated(try another one)");
                return true;
            }
            signInUpHandler.AddPerson(command.getCommand(), command.getArg());
            Cli.state = "sign in or sign up";
            return true;
        }
        return false;
    }

    public boolean SignIn(Command command) {
        //todo  change state
        if (Cli.state.equals("sign in")) {
            if (command.getArg() == null && command.getCommand().equals("back")) {
                Cli.state = "sign in or sign up";
                return true;
            }
            if (signInUpHandler.IsThisPersonValid(command.getCommand(), command.getArg())) {
                if (command.getCommand().equals("admin")) {
                    Cli.state = "adminPanel";
                    Cli.AmIAdmin = true;
                } else {
                    Cli.state = "studentPanel";
                    Cli.AmIAdmin = false;

                }
                return true;
            } else {
                System.out.println("this UserName or password is not valid");
                return true;
            }
        }
        return false;
    }

    public boolean si_or_SU(Command command) {
        if (command.getArg() == null) {
            if (command.getCommand().equals("signup")) {
                Cli.state = "sign up";
                return true;
            } else if (command.getCommand().equals("signin")) {
                Cli.state = "sign in";
                return true;
            }
        }
        return false;
    }

    public boolean seeListOfCollege(Command command) {
        if (Cli.state.equals("studentPanel") || Cli.state.equals("adminPanel")) {
            if (command.getArg() == null && command.getCommand().equals("back")) {
                Cli.state = "sign in";
                return true;
            }
            if (command.getArg() == null && command.getCommand().equals("ld")) {
                for (College c : university.collegesList) {
                    System.out.println(c);
                }
                Cli.state = "CollegeList";
                return true;
            }
        }
        return false;
    }

    public boolean seetheCourses(Command command) {
        if (Cli.state.equals("CollegeList")) {
            if (command.getCommand().equals("back") && command.getArg() == null && Cli.AmIAdmin) {
                Cli.state = "adminPanel";
                return true;
            } else if (command.getCommand().equals("back") && command.getArg() == null && !Cli.AmIAdmin) {
                Cli.state = "studentPanel";
                return true;
            }
            boolean IFindIt = false;
            for (College c : university.collegesList) {
                if (command.getCommand().equals(c.getName()) && command.getArg() == null) {
                    IFindIt = true;
                    if (c.getListOfCourses().isEmpty()) {
                        System.out.println("no courses for this college");
                        return true;
                    }
                    System.out.println("NAME  :: code,Unit,teacher,StartTime,examDat,examtime,type,capacity,numOfStu");
                    for (courses cou : c.getListOfCourses()) {
                        System.out.println(cou);
                    }
                }
            }
            if (!IFindIt){
                System.out.println("no department with this name");
            }
            return true;
        }
        return false;
    }
}
