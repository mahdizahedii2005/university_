package Appliction.Panel;

import Appliction.Panel.College.College;

import java.util.ArrayList;

public abstract class Panel {
    private univercity univercity ;
    private String userName ;
    private String password ;
    private boolean access ;
    public Panel(String userName, String password, boolean access) {
        univercity = new univercity("Sharif");
        this.userName = userName;
        this.password = password;
        this.access = access;
    }

    public ArrayList<College> list_of_College(){
        return univercity.getColleges();
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
