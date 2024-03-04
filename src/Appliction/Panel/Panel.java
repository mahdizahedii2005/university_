package Appliction.Panel;

import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.courses;

import java.util.ArrayList;

public abstract class Panel {
    private university univercity;
    private String userName;
    private String password;

    public Panel(String userName, String password) {
        univercity = new university("Sharif");
        this.userName = userName;
        this.password = password;
    }

    public ArrayList<College> list_of_College() {
        return univercity.getCollegesList();
    }

    public ArrayList<courses> list_of_courses(College college) {
        return college.getListOfCourses();
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

}
