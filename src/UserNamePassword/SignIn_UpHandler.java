package UserNamePassword;

import Appliction.Methods.CliMethodNeeded;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SignIn_UpHandler {
    String add = "src\\UserNamePassword\\pass_and_username.txt";
    File passFile;
    Scanner ScannerPass;
    PrintWriter writerPass;
    FileWriter passWriter_NoUse;

    public SignIn_UpHandler() {
        try {
            this.passFile = new File(add);
            this.ScannerPass = new Scanner(passFile);
            this.passWriter_NoUse = new FileWriter(passFile, true);
            this.writerPass = new PrintWriter(passWriter_NoUse, true);
        } catch (IOException a) {
            System.out.println("Cant find the sign in txt");
        }
    }

    public void AddPerson(String UserName, String Password) {
        writerPass.println(UserName + ":" + Password);
    }

    public boolean IsThisPersonValid(String userName, String Password) {
        while (ScannerPass.hasNextLine()) {
            String line = ScannerPass.nextLine();
            String RealUserName = CliMethodNeeded.ReturnFirstWord(line);
            String RealPassword = CliMethodNeeded.ReturnSecondWord(line);
            if (RealPassword.equals(Password) && RealUserName.equals(userName)) {
                return true;
            }
        }
        return false;
    }
}
