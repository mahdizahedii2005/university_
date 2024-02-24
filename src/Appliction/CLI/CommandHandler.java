package Appliction.CLI;

import Appliction.Panel.College.College;
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
                HelpResult = "enter a user name and password of your account";
            } else if (Cli.state.equals("sign up")) {
                HelpResult = "enter a user name and password that you want for your account";
            } else if (Cli.state.equals("adminPanel")) {
                HelpResult = "lc -> for see the list of the College\n";//todo
            } else if (Cli.state.equals("studentPanel")) {
                HelpResult = "";//todo
            }
        }
        return HelpResult;
    }

    public boolean SignUp(Command command) {
        if (Cli.state.equals("sign up")) {
            if (command.getArg() == null) {
                return false;
            } else {
                if (signInUpHandler.DoWeHaveThis(command.getCommand())) {
                    System.out.println("The username is repeated(try another one)");
                    return true;
                }
                signInUpHandler.AddPerson(command.getCommand(), command.getArg());
                Cli.state = "sign in or sign up";
                return true;
            }
        }
        return false;
    }

    public boolean SignIn(Command command) {
        //todo  change state
        if (Cli.state.equals("sign in")) {
            if (command.getArg() == null) {
                return false;
            } else {
                if (signInUpHandler.IsThisPersonValid(command.getCommand(), command.getArg())) {
                    if (command.getCommand().equals("admin")) {
                        Cli.state = "adminPanel";
                    } else {
                        Cli.state = "studentPanel";
                    }
                    return true;
                }
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
        if (command.getArg() == null && command.getCommand().equals("lc")) {
            for (College c : university.collegesList) {
                System.out.println(c);
            }
            return true;
        }
        return false;
    }
}
