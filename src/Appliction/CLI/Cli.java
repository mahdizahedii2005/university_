import java.util.ArrayList;
public class Cli {
    private Shell shell;
    private String computerName;
    private String user;

    public Cli(String computerName, String user) {
        this.user = user;
        this.computerName = computerName;
        Directory first = new Directory("~",null,0);
        Shell shell = new Shell(user,computerName,first);
        this.shell=shell;
    }
    public String processCommand(Command command) {
        if (command.getCommand().equals("ls")) {
            return shell.listStatus();
        }
        if (command.getCommand().equals("cd")) {
            return shell.changeDirectory(command.getArg());
        }
        if (command.getCommand().equals("pwd")) {
            return shell.printWorkingDirectory();
        }
        if (command.getCommand().equals("rm")) {
            return shell.removeFile(command.getArg());
        }
        if (command.getCommand().equals("touch")) {
            return shell.createFile(command.getArg());
        }
        if (command.getCommand().equals("rmdir")) {
            return shell.removeDirectory(command.getArg());
        }
        if (command.getCommand().equals("mkdir")) {
            return shell.createDirectory(command.getArg());
        }
        return command.getCommand()+": command not found";
    }


    public String getHeader() {
        Directory working = shell.getWorkingDirectory();
        String first = user + "@" + computerName + ":~";
        ArrayList<String> nasl = new ArrayList<>();
        nasl =working.getDirectory();
        for (int i = nasl.size()-1; i >= 0 ; i=i-1) {
            if (i==nasl.size()-1&&nasl.get(nasl.size()-1).equals("~")){
                continue;
            }
            first = first + "/"+nasl.get(i);
        }
        first = first + "$";
        return first;
    }
}
