package Appliction.Panel;
public class Panel {
    private String userName ;
    private String password ;
    private boolean access ;
    public Panel(String userName, String password, boolean access) {
        this.userName = userName;
        this.password = password;
        this.access = access;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
