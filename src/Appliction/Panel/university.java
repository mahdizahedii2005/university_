package Appliction.Panel;

import Appliction.Panel.College.College;

import java.util.ArrayList;

public class university {
    private ArrayList<College> colleges;
    private String name;
    public university(String name) {
        colleges = new ArrayList<>();
        this.name = name;
    }


    public ArrayList<College> getColleges() {
        return colleges;
    }

    public void setColleges(ArrayList<College> colleges) {
        this.colleges = colleges;
    }
    public void addCollege(College college){
        this.colleges.add(college);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}