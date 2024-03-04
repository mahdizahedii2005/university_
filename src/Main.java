import Appliction.Application;
import Appliction.SaveData.AdminSave;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new Application().run();
        AdminSave adminSave = new AdminSave();
        adminSave.Export("src\\mamad");
    }
}