package Appliction.CLI;

import Appliction.Application;
import UserNamePassword.SignIn_UpHandler;

public  class CommandHandler {
    SignIn_UpHandler signInUpHandler = new SignIn_UpHandler();

    public boolean SignUp(Command command) {
        //todo  change state
        if (Cli.state.equals("sign up")) {
            if (command.getArg() == null) {
                return false;
            } else {
                signInUpHandler.AddPerson(command.getCommand(), command.getArg());
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
                signInUpHandler.IsThisPersonValid(command.getCommand(), command.getArg());
                System.out.println("sign in shodam");
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
}
