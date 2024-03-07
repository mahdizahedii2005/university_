package Appliction.Panel;

import Appliction.Panel.College.Course.courses;

import java.util.ArrayList;

public class Student extends Panel {
    private int Units_piked = 0;
    private int general_unit = 0;
    private ArrayList<courses> coursesArrayList = new ArrayList<>();

    public int getGeneral_unit() {
        return general_unit;
    }

    public void restart() {
        general_unit = 0;
        Units_piked = 0;
        coursesArrayList = new ArrayList<>();
    }

    public Student(String userName, String password) {
        super(userName, password);
        university.StudentList.add(this);
    }

    @Override
    public String toString() {
        return getUserName() + " -> unit piked:" + Units_piked;
    }

    public String PikeCourses(courses courses) {
        if (courses.getType().equals("general")) {
            if (AlreadyHad(courses.getCode())) {
                return "The operation was not successful(Already you had this course)";
            } else if (courses.getUnit() + general_unit > 5) {
                return "The operation was not successful(limit of general unit)";
            } else if (courses.getUnit() + Units_piked > 20) {
                return "The operation was not successful(limit of 20 unit)";
            } else if (courses.getCapacity() < courses.getNumberOfStudent() + 1) {
                return "The operation was not successful(capacity limit)";
            } else if (checkExamTime(courses)) {
                return "The operation was not successful(Exam time interference)";
            } else if (checkClassTime(courses)) {
                return "The operation was not successful(class time interference)";
            } else {
                coursesArrayList.add(courses);
                courses.increaseNumStudent();
                general_unit = general_unit + courses.getUnit();
                Units_piked = Units_piked + courses.getUnit();
                courses.AddListOfStudent(this);
                return "The operation was successful";
            }
        } else {
            if (AlreadyHad(courses.getCode())) {
                return "The operation was not successful(Already you had this course)";
            } else if (courses.getUnit() + Units_piked > 20) {
                return "The operation was not successful(limit of 20 unit)";
            } else if (courses.getCapacity() < courses.getNumberOfStudent() + 1) {
                return "The operation was not successful(limit capacity limit)";
            } else if (checkExamTime(courses)) {
                return "The operation was not successful(Exam time interference)";
            } else if (checkClassTime(courses)) {
                return "The operation was not successful(class time interference)";
            } else {
                coursesArrayList.add(courses);
                courses.increaseNumStudent();
                Units_piked = Units_piked + courses.getUnit();
                courses.AddListOfStudent(this);
                return "The operation was successful";
            }
        }
    }

    public String DeleteCourse(courses courses) {
        if (coursesArrayList.remove(courses)) {
            courses.DecreaseNumStudent();
            courses.deleteStudent(this);
            Units_piked = Units_piked - courses.getUnit();
            if (courses.getType().equals("general")) {
                general_unit = general_unit - courses.getUnit();
            }
            return "The operation was successful";
        } else {
            return "The operation was not successful(you dont have this course)";
        }
    }

    private boolean AlreadyHad(int code) {
        for (courses coursePiked : coursesArrayList) {
            if (coursePiked.getCode() == code) {
                return true;
            }
        }
        return false;
    }

    private boolean checkExamTime(courses courses) {
        for (courses coursesPiked : coursesArrayList) {
            if (coursesPiked.getExamTime().equals(courses.getExamTime()) && coursesPiked.getExamDate().equals(courses.getExamDate())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkClassTime(courses courses) {
        for (courses coursesPiked : coursesArrayList) {
            if (coursesPiked.getStartTime().equals(courses.getStartTime())) {
                return true;
            }
        }
        return false;
    }

    public int getUnits_piked() {
        return Units_piked;
    }

    public void setUnits_piked(int units_piked) {
        Units_piked = units_piked;
    }

    public ArrayList<Appliction.Panel.College.Course.courses> getCoursesArrayList() {
        return coursesArrayList;
    }

    public void setCoursesArrayList(ArrayList<Appliction.Panel.College.Course.courses> coursesArrayList) {
        this.coursesArrayList = coursesArrayList;
    }

    public void AddCourse(courses courses) {
        this.coursesArrayList.add(courses);
    }
}
