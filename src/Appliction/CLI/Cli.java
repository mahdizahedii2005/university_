package Appliction.CLI;
public class Cli {
    private Shell shell;

    public Cli(String computerName, String user) {
    }

    public String processCommand(Command command) {

        return command.getCommand() + ": command not found";
    }


    public String getHeader() {
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
//        return first;
        return null;
    }
}
