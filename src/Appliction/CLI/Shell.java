import java.util.ArrayList;

public class Shell {
    private Directory workingDirectory;
    private String computerName;
    private String user;

    public Shell(String user, String computerName, Directory workingDirectory) {
        this.user = user;
        this.computerName = computerName;
        this.workingDirectory = workingDirectory;
    }

    // ls
    public String listStatus() {
        String result = "";
        for (int i = 0; i < workingDirectory.getContents().size(); i++) {
            if (i == workingDirectory.getContents().size() - 1) {
                result = result + workingDirectory.getContents().get(i).getName() ;
                if (workingDirectory.getContents().get(i).getType().equals("d")){
                    result = result +"/";
                }
                break;
            }
            result = result + workingDirectory.getContents().get(i).getName();
            if (workingDirectory.getContents().get(i).getType().equals("d")){
                result = result +"/";
            }
            result = result + "\n";
        }
        return result;
    }

    // pwd
    public String printWorkingDirectory() {
        String result = "/home";
        if (workingDirectory.getName().equals("~")) {
            return result ;
        }
        ArrayList<String> nasl = new ArrayList<>();
        nasl = workingDirectory.getDirectory();
        for (int i = nasl.size() - 1; i >= 0; i = i - 1) {
            if (i == nasl.size() - 1 && nasl.get(nasl.size() - 1).equals("~")) {
                continue;
            }
            result = result + "/" + nasl.get(i);
        }
        return result;
    }

    // cd directory_name
    public String changeDirectory(String name) {
        if (name.equals("..")) {
            if (workingDirectory.getParentDirectory() == null) {
                return "";
            }
            workingDirectory = workingDirectory.getParentDirectory();
            return "";
        }
        for (Directory Directory : workingDirectory.contentsDirectory) {
            if (Directory.getName().equals(name)) {
                workingDirectory = Directory;
                return "";
            }
        }
        return ("cd: '" + name + "': No such file or directory");
    }

    // mkdir directory_name
    public String createDirectory(String name) {
        if (name.equals("..")) {
            return ("mkdir: cannot create directory '" + name + "': File exists");
        }
        for (Directory Directory : workingDirectory.getContentsDirectory()) {
            if (Directory.getName().equals(name)) {
                return ("mkdir: cannot create directory '" + name + "': File exists");
            }
        }
        Directory newdic = new Directory(name, workingDirectory, workingDirectory.deap + 1);
        workingDirectory.addContentDic(newdic);
        workingDirectory.addcontent(newdic);
        return "";
    }

    // rmdir directory_name
    public String removeDirectory(String name) {
        for (int i = 0; i < workingDirectory.getContentsDirectory().size(); i++) {
            if (workingDirectory.getContentsDirectory().get(i).getName().equals(name)) {
                workingDirectory.getContents().remove(workingDirectory.getContentsDirectory().get(i));
                workingDirectory.getContentsDirectory().remove(i);
                return "";
            }
        }
        return "rmdir: failed to remove '" + name + "': No such file or directory";
    }

    // touch file_name
    public String createFile(String value) {
        for (int i = 0; i < workingDirectory.getContentsFile().size(); i++) {
            if (workingDirectory.getContentsFile().get(i).getName().equals(value)) {
                return "mkdir: cannot create directory '{" + value + "}': File exists.";
            }
        }
        File newfile = new File(value, workingDirectory.getDeap() + 1);
        workingDirectory.getContentsFile().add(newfile);
        workingDirectory.getContents().add(newfile);
        return "";

    }

    // rm file_name
    public String removeFile(String value) {
        for (File File : workingDirectory.getContentsFile()) {
            if (File.getName().equals(value)) {
                workingDirectory.getContents().remove(File);
                workingDirectory.getContentsFile().remove(File);
                return "";
            }
        }
        for (Directory Directory : workingDirectory.contentsDirectory) {
            if (Directory.getName().equals(value)) {
                return "rm: cannot remove '" + value + "/': Is a directory";
            }
        }
        return "rm: cannot remove '" + value + "': No such file or directory";
    }

    public Directory getWorkingDirectory() {
        return workingDirectory;
    }
}
