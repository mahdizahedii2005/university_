package Appliction;

import Appliction.CLI.Cli;
import Appliction.CLI.Command;
import Appliction.CLI.CommandHandler;
import Appliction.Methods.CliMethodNeeded;
import Appliction.hardcode.addCollegeAndCourses;

import java.util.Scanner;

public class Application implements Runnable {
    CliMethodNeeded cliMethodNeeded;
    private final Cli cli;
    private final Scanner sc;
    public static final String ERROR = "Syntax Invalid(watch out for extra Space or use code help)";

    public Application() {
        cli = new Cli();
        sc = new Scanner(System.in);
        cliMethodNeeded = new CliMethodNeeded();
    }

    @Override
    public void run() {
        new addCollegeAndCourses().run();
        while (true) {
            try {
                if (CommandHandler.creatState == 0) {
                    System.out.print(cli.getHeader());
                }
                String input = sc.nextLine();
                if (input.equals("execute")) {
                    break;
                }
                if (cliMethodNeeded.IsItFirstSpace(input) || cliMethodNeeded.IsIsLastSpace(input)) {
                    System.out.println(ERROR);
                } else {
                    if (cliMethodNeeded.IsTwoWord(input)) {
                        System.out.print(cli.processCommand(new Command(cliMethodNeeded.ReturnFirstWord(input), cliMethodNeeded.ReturnSecondWord(input))));
                    } else {
                        System.out.print(cli.processCommand(new Command(input)));
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ERROR);
            }
        }
        System.out.println("TANKS FOR USING THIS APP");
        System.out.println("Written by MAHDI :)");
    }
}
