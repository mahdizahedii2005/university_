package Appliction.Panel;

import Appliction.Panel.College.Course.courses;

import java.util.ArrayList;

public class Student extends Panel {
    private int Units_piked = 0;
    private ArrayList<courses> courses = new ArrayList<>();
    public Student(String userName, String password, boolean access) {
        super(userName, password, access);
    }
}
