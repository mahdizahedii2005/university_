package Appliction.Panel.College;

import Appliction.Panel.College.Course.courses;
import java.util.ArrayList;

public class College {
    final String Name;
    private ArrayList<courses> ListOfCourses = new ArrayList<>();
    public College(String name) {
        Name = name;
    }

    public ArrayList<courses> getListOfCourses() {
        return ListOfCourses;
    }

    public void setListOfCourses(ArrayList<courses> listOfCourses) {
        ListOfCourses = listOfCourses;
    }
    public void addListOfCourses(courses courses){
        this.ListOfCourses.add(courses);
    }

    public String getName() {
        return Name;
    }

    @Override

    public String toString() {
        return Name;
    }
}
