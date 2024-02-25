package Appliction.hardcode;

import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.courses;
import Appliction.Panel.university;

public class addCollegeAndCourses extends HardCode {
    @Override
    public  void run() {
        College math = university.CreatCollege("math");
        math.addListOfCourses(new courses(30, 20026, 4, "dr.mohamadi", "bp", "10:00", "09:00", "1403/3/30", "general", true, math));
        math.addListOfCourses(new courses(70, 25026, 4, "dr.mostajeran", "ap", "11:00", "09:00", "1403/3/10", "general", true, math));
        math.addListOfCourses(new courses(10, 25726, 3, "dr.pornaki", "jabr", "01:00", "09:00", "1403/3/10", "t", true, math));
        College phisics = university.CreatCollege("phicis");
        phisics.addListOfCourses(new courses(30, 2026, 4, "dr.mohamadi", "bp", "10:00", "09:00", "1403/3/30", "general", true, phisics));
        phisics.addListOfCourses(new courses(70, 2026, 4, "dr.mostajeran", "ap", "11:00", "09:00", "1403/3/10", "general", true, phisics));
        phisics.addListOfCourses(new courses(10, 2726, 3, "dr.pornaki", "jabr", "01:00", "09:00", "1403/3/10", "t", true, phisics));

        College ce = university.CreatCollege("ce");
        ce.addListOfCourses(new courses(30, 20126, 4, "dr.mohamadi", "bp", "18:00", "09:00", "1402/3/30", "general", true, ce));
        ce.addListOfCourses(new courses(70, 20526, 4, "dr.mostajeran", "ap", "01:00", "09:00", "1405/3/10", "general", true, ce));
        ce.addListOfCourses(new courses(10, 21726, 3, "dr.pornaki", "jabr", "16:00", "09:00", "1404/3/10", "t", true, ce));
    }
}
