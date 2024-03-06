package Appliction.hardcode;

import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.courses;
import Appliction.Panel.Student;
import Appliction.Panel.university;

public class addCollegeAndCourses extends HardCode {
    @Override
    public void run() {
        Student d1c = new Student("402109731", "1234");
        Student d2 = new Student("402715155", "1234");
        Student d3 = new Student("402777781", "1234");
        Student d4 = new Student("402199931", "1234");
        College civil = university.CreatCollege("civil");
        civil.addListOfCourses(new courses(30, 10202, 3, "dr.mohamadi", "bp", "10:00", "09:00", "1403/3/30", "general", true, civil));
        civil.addListOfCourses(new courses(70, 85285, 3, "dr.mostajeran", "ap", "11:00", "09:00", "1403/3/10", "general", true, civil));
        civil.addListOfCourses(new courses(10, 77787,10, "dr.pornaki", "jabr", "01:00", "09:00", "1403/3/10", "t", true, civil));
        College math = university.CreatCollege("math");
        math.addListOfCourses(new courses(30, 20026, 1, "dr.mohamadi", "bp", "10:00", "09:00", "1403/3/30", "general", true, math));
        math.addListOfCourses(new courses(70, 25026, 1, "dr.mostajeran", "ap", "11:00", "09:00", "1403/3/10", "general", true, math));
        math.addListOfCourses(new courses(10, 25726, 5, "dr.pornaki", "jabr", "01:00", "09:00", "1403/3/10", "t", true, math));
        College phisics = university.CreatCollege("phicis");
        phisics.addListOfCourses(new courses(30, 2026, 1, "dr.mohamadi", "bp", "10:00", "09:00", "1403/3/30", "general", true, phisics));
        phisics.addListOfCourses(new courses(70, 2026, 2, "dr.mostajeran", "ap", "11:00", "09:00", "1403/3/10", "general", true, phisics));
        phisics.addListOfCourses(new courses(10, 2726, 4, "dr.pornaki", "jabr", "01:00", "09:00", "1403/3/10", "t", true, phisics));

        College ce = university.CreatCollege("ce");
        ce.addListOfCourses(new courses(30, 20126, 1, "dr.mohamadi", "bp", "18:00", "09:00", "1402/3/30", "t", true, ce));
        ce.addListOfCourses(new courses(70, 20526, 2, "dr.mostajeran", "ap", "01:00", "09:00", "1405/3/10", "t", true, ce));
        ce.addListOfCourses(new courses(10, 21726, 3, "dr.pornaki", "jabr", "16:00", "09:00", "1404/3/10", "t", true, ce));
        for (College coll : university.collegesList) {
            for (courses cou : coll.getListOfCourses()) {
                d1c.PikeCourses(cou);
                d2.PikeCourses(cou);
                d3.PikeCourses(cou);
                d4.PikeCourses(cou);
            }
        }
    }
}
