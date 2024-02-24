package Appliction;

import Appliction.CLI.Cli;
import Appliction.CLI.Command;
import Appliction.Methods.CliMethodNeeded;

import java.util.Scanner;

public class Application implements Runnable {
    CliMethodNeeded cliMethodNeeded;
    private final Cli cli;
    private final Scanner sc;
    public static  final String Error = "Syntax Invalid(watch out for extra Space";

    public Application()  {
        cli = new Cli();
        sc = new Scanner(System.in);
        cliMethodNeeded = new CliMethodNeeded();
    }
    @Override
    public void run() {
        while (true) {
            System.out.print(cli.getHeader());
            String input = sc.nextLine();
            if (cliMethodNeeded.IsItFirstSpace(input) || cliMethodNeeded.IsIsLastSpace(input)) {
                System.out.println(Error);
            }else {
                if (cliMethodNeeded.IsTwoWord(input)){
                    cli.processCommand(new Command(cliMethodNeeded.ReturnFirstWord(input),cliMethodNeeded.ReturnSecondWord(input)));
                }else {
                    cli.processCommand(new Command(input));
                }
            }
        }
    }
}
