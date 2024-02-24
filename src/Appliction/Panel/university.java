package Appliction.Panel;

import Appliction.Panel.College.College;

import java.util.ArrayList;

public class university {
    public static ArrayList<College> collegesList;
    private String name;
    public university(String name) {
        collegesList = new ArrayList<>();
        this.name = name;
    }
    public ArrayList<College> getCollegesList() {
        return collegesList;
    }
    public void setCollegesList(ArrayList<College> collegesList) {
        this.collegesList = collegesList;
    }
    public void addCollege(College college){
        collegesList.add(college);
    }
    public void CreatCollege(String Name){
        College college1 = new College(Name);
        collegesList.add(college1);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}