package Appliction.Panel;

import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.courses;

import java.util.ArrayList;

public class Admin extends Panel {
    public Admin(String userName, String password, boolean access) {
        super(userName, password);
    }

    public void creat_courses(int capacity, int code, int Unit, String teacher, String name, String startTime, String examTime, String examDate, String type, boolean available, College college) {
        courses newCourses = new courses(capacity, code, Unit, teacher, name, startTime, examTime, examDate, type, available, college);
        college.addListOfCourses(newCourses);
    }

    public void increaseCapacity(courses courses, int NumInncrease) {
        courses.increaseCapacity(NumInncrease);
    }

    public ArrayList<Student> ListStudent(courses courses) {
        return courses.getListOfStudent();
    }

    public void deleteStudent(courses courses, Student student) {
        courses.deleteStudent(student);
    }
}
