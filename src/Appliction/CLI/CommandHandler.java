package Appliction.CLI;

import Appliction.Application;
import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.*;
import Appliction.Panel.Student;
import Appliction.SaveData.AdminSave;
import UserNamePassword.SignIn_UpHandler;
import Appliction.Panel.university;

public class CommandHandler {
    courseCreat coursesCreat = new courseCreat();
    public static int creatState = 0;
    SignIn_UpHandler signInUpHandler = new SignIn_UpHandler();

    public boolean help(Command command) {
        String HelpResult = "";
        if (command.getCommand().equals("help") && command.getArg() == null) {
            HelpResult = "loginstate - > go to the login state\nexecute -> to stop the program\n";
            if (Cli.state.equals("sign in or sign up")) {
                HelpResult += "signin -> enter to your account\nsignup -> creat an account";
            } else if (Cli.state.equals("sign in")) {
                HelpResult += "back -> back to the sign in or sign up state\nenter a user name and password of your account";
            } else if (Cli.state.equals("sign up")) {
                HelpResult += "back -> back to the sign in or sign up state\nenter a user name and password that you want for your account";
            } else if (Cli.state.equals("adminPanel")) {
                HelpResult += "back -> back to the sign in state\nld -> for see the list of the Department\nmkcou -> creat  new course\nmkdep + name -> creat new department\nexport + path -> for save the information of the university to this path\nimport + path -> for load the information of university on this path\nexportf + path ->for save the information of the university forever";
            } else if (Cli.state.equals("studentPanel")) {
                HelpResult += "back -> back to the sign in\nld -> for see the list of the Department\nlpc -> to see your picked course";//todo
            } else if (Cli.state.equals("CollegeList")) {
                HelpResult += "back -> back to the Panel state\nname any department -> see the department courses";
            } else if (Cli.state.equals("coursesList") && Cli.AmIAdmin) {
                HelpResult += "back -> back to the departmentList state\ncode of any courses -> see the List of the student of the courses";
            } else if (Cli.state.equals("coursesList") && !Cli.AmIAdmin) {
                HelpResult += "back -> back to the departmentList state\nadd + code -> to pick the course";
            } else if (creatState > 0) {
                HelpResult += "back -> Return to the previous step\n break -> break course creating";
            } else if (Cli.state.equals("studentCourseList")) {
                HelpResult += "back -> return to the courseList\nadd + username -> enter an user name of any Student to add the Student to this course\ndel + user name -> enter an user name of any Student in the course to delete this student from this course\ninc + number -> for increase the capacity of the course";
            } else if (Cli.state.equals("ownCourse")) {
                HelpResult += "back -> return to the Panel\ndel + course code -> enter  del +an code to delete that course from of your Picked List Course";
            }
        }
        if (HelpResult.equals("")) {
            return false;
        }
        System.out.println(HelpResult);
        return true;
    }
    public boolean exportf(Command command){
        if (command.getCommand().equals("exportf")&&Cli.AmIAdmin&&Cli.state.equals("adminPanel")){
            AdminSave adminSave = new AdminSave();
            adminSave.Export("src\\Appliction\\Savedata");
            return true;
        }
        return false;
    }

    public boolean Importt(Command command) {
        try {

            if (Cli.state.equals("adminPanel") && Cli.AmIAdmin && command.getCommand().equals("import")) {
                AdminSave adminSave = new AdminSave();
                adminSave.Import(command.getArg());
                return true;
            }
        } catch (NullPointerException n) {

        }
        return false;
    }

    public boolean Export(Command command) {
        try {
            if (Cli.state.equals("adminPanel") && Cli.AmIAdmin && command.getCommand().equals("export")) {
                AdminSave adminSave = new AdminSave();
                adminSave.Export(command.getArg());
                return true;
            }
        } catch (NullPointerException n) {
        }
        return false;
    }

    private boolean doWeaveThisCollege(String name) {
        for (College coll : university.collegesList) {
            if (coll.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean creatCollege(Command command) {
        if (Cli.state.equals("adminPanel") && creatState == 0) {
            if (command.getCommand().equals("mkdep") && command.getArg() != null) {
                if (!doWeaveThisCollege(command.getArg())) {
                    university.CreatCollege(command.getArg());
                    System.out.println("The operation was successful");
                    return true;
                } else {
                    System.out.println("this department is already exist");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean SignUp(Command command) {
        if (Cli.state.equals("sign up")) {
            if (command.getArg() == null && command.getCommand().equals("back")) {
                Cli.state = "sign in or sign up";
                return true;
            }
            if (signInUpHandler.DoWeHaveThis(command.getCommand())) {
                System.out.println("The username is repeated(try another one)");
                return true;
            }
            signInUpHandler.AddPerson(command.getCommand(), command.getArg());
            new Student(command.getCommand(), command.getArg());
            new AdminSave().Export(("src\\Appliction\\Savedata"));
            Cli.state = "sign in or sign up";
            return true;
        }
        return false;
    }

    public boolean SignIn(Command command) {
        if (Cli.state.equals("sign in")) {
            if (command.getArg() == null && command.getCommand().equals("back")) {
                Cli.state = "sign in or sign up";
                return true;
            }
            if (signInUpHandler.IsThisPersonValid(command.getCommand(), command.getArg())) {
                if (command.getCommand().equals("admin")) {
                    Cli.state = "adminPanel";
                    Cli.AmIAdmin = true;
                } else {
                    Cli.AmIAdmin = false;
                    for (Student stu : university.StudentList) {
                        if (stu.getUserName().equals(command.getCommand())) {
                            Cli.curentStudent = stu;
                            Cli.state = "studentPanel";
                            return true;
                        }
                    }
                    System.out.println("cant find student with this username and password");
                }
                return true;
            } else {
                System.out.println("this UserName or password is not valid");
                return true;
            }
        }
        return false;
    }

    public boolean si_or_SU(Command command) {
        if (command.getArg() == null && Cli.state.equals("sign in or sign up")) {
            if (command.getCommand().equals("signup")) {
                Cli.state = "sign up";
                return true;
            } else if (command.getCommand().equals("signin")) {
                Cli.state = "sign in";
                return true;
            }
        }
        return false;
    }

    public boolean seeListOfCollege(Command command) {
        if ((Cli.state.equals("studentPanel") || Cli.state.equals("adminPanel")) && creatState == 0) {
            if (command.getArg() == null && command.getCommand().equals("back")) {
                Cli.state = "sign in";
                return true;
            }
            if (command.getArg() == null && command.getCommand().equals("ld")) {
                System.out.println("List of the departments of the university:");
                for (College c : university.collegesList) {
                    System.out.println(c);
                }
                Cli.state = "CollegeList";
                return true;
            }
        }
        return false;
    }

    public boolean seetheCourses(Command command) {
        if (Cli.state.equals("CollegeList") && creatState == 0) {
            if (command.getCommand().equals("back") && command.getArg() == null && Cli.AmIAdmin) {
                Cli.state = "adminPanel";
                return true;
            } else if (command.getCommand().equals("back") && command.getArg() == null && !Cli.AmIAdmin) {
                Cli.state = "studentPanel";
                return true;
            }
            boolean IFindIt = false;
            for (College c : university.collegesList) {
                if (command.getCommand().equals(c.getName()) && command.getArg() == null) {
                    IFindIt = true;
                    Cli.curentCollege = c;
                    if (c.getListOfCourses().isEmpty()) {
                        System.out.println("no courses for this college");
                        return true;
                    }
                    System.out.println("List of the course of the " + Cli.curentCollege + "department");
                    System.out.println("NAME ->code,Unit,teacher,StartTime,examDat,examtime,type,capacity,numOfStu,department");
                    for (courses cou : c.getListOfCourses()) {
                        System.out.println(cou);
                    }
                }
            }
            if (!IFindIt && !command.getCommand().equals("help")) {
                System.out.println("no department with this name");
            } else {
                Cli.state = "coursesList";
            }
            return true;
        }
        return false;
    }

    public boolean seetheStudent(Command command) {
        if (command.getArg() == null && Cli.state.equals("coursesList") && creatState == 0 && Cli.AmIAdmin) {
            if (command.getCommand().equals("back")) {
                Cli.state = ("CollegeList");
                return true;
            }
            int code;
            try {
                code = Integer.parseInt(command.getCommand());
            } catch (NumberFormatException e) {
                System.out.println(Application.ERROR);
                return true;
            }
            boolean f = false;
            for (courses cou : Cli.curentCollege.getListOfCourses()) {
                if (cou.getCode() == code) {
                    f = true;
                    Cli.curentCourse = cou;
                    if (cou.getListOfStudent().isEmpty()) {
                        System.out.println("this course is empty");
                        return true;
                    }
                    for (Student student : cou.getListOfStudent()) {
                        System.out.println(student);
                    }
                }
            }
            if (!f) {
                System.out.println("no courses find with this code");
            } else {
                Cli.state = "studentCourseList";
            }
            return true;
        }
        return false;
    }

    public boolean addStudentCourse(Command command) {
        if (Cli.state.equals("studentCourseList") && creatState == 0) {
            try {
                if (command.getCommand().equals("add")) {
                    for (Student student : university.StudentList) {
                        if (command.getArg().equals(student.getUserName())) {
                            System.out.println(student.PikeCourses(Cli.curentCourse));
                            return true;
                        }
                    }
                    System.out.println("no student with this username found");
                    return true;
                }
            } catch (NullPointerException n) {
                return false;
            }
        }
        return false;
    }

    public boolean backChecker(Command command) {
        if (command.getCommand().equals("back")) {
            if (Cli.state.equals("studentCourseList")) {
                Cli.curentCourse = null;
                Cli.state = "coursesList";
                return true;
            }
        }
        return false;
    }

    private boolean doWeaveThiscode(int code) {
        for (College college : university.collegesList) {
            for (courses cou : college.getListOfCourses()) {
                if (code == cou.getCode()) {
                    return false;
                }
            }
        }
        return true;
    }

    //    public boolean deleteStudent(Command command) {
//        if (Cli.state.equals("studentCourseList") && creatState == 0) {
//            try {
//                if (command.getCommand().equals("del")) {
//                    for (Student student : Cli.curentCourse.ListOfStudent) {
//                        if (command.getArg().equals(student.getUserName())) {
//                            System.out.println(student.DeleteCourse(Cli.curentCourse));
//                            return true;
//                        }
//                    }
//                    System.out.println("no  student with this username");
//                    return true;
//                }
//            } catch (NullPointerException n) {
//                return false;
//            }
//        }
//        return false;
//    }
    public boolean addCourse(Command command) {
        if (Cli.state.equals("adminPanel") && Cli.AmIAdmin) {
            if (courseCreatBreak(command) || courseCreatBack(command)) {
                return true;
            }
            if (command.getCommand().equals("mkcou") && creatState == 0) {
                System.out.println("course name:");
                creatState++;
                return true;
            }
            if (creatState == 1) {
                System.out.println("code:");
                creatState++;
                coursesCreat.setName(command.getCommand());
                return true;

            } else if (creatState == 2) {
                try {
                    if (doWeaveThiscode(Integer.parseInt(command.getCommand()))) {
                        System.out.println("Unit:");
                        creatState++;
                        coursesCreat.setCode(Integer.parseInt(command.getCommand()));
                        return true;
                    } else {
                        System.out.println("this code is already use by another course (Please try a new code:)");
                        return true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("please enter correct input(NumberFormatException)");
                    System.out.println("code:");
                    return true;
                }
            } else if (creatState == 3) {
                System.out.println("capacity:");
                creatState++;
                try {
                    coursesCreat.setUnit(Integer.parseInt(command.getCommand()));
                } catch (NumberFormatException e) {
                }
                return true;

            } else if (creatState == 4) {
                System.out.println("teacher name:");
                creatState++;
                try {
                    coursesCreat.setCapacity(Integer.parseInt(command.getCommand()));
                } catch (NumberFormatException e) {
                }

                return true;

            } else if (creatState == 5) {
                System.out.println("start time(example:-> 09:00) :");
                creatState++;
                coursesCreat.setTeacher((command.getCommand()));
                return true;

            } else if (creatState == 6) {
                System.out.println("exam time(example:-> 09:00) :");
                creatState++;
                coursesCreat.setStartTime((command.getCommand()));
                return true;

            } else if (creatState == 7) {
                System.out.println("exam date (example:-> 1403/3/30) :");
                creatState++;
                coursesCreat.setExamTime((command.getCommand()));
                return true;

            } else if (creatState == 8) {
                System.out.println("type(general or professional):");
                creatState++;
                coursesCreat.setExamDate((command.getCommand()));
                return true;
            } else if (creatState == 9) {
                if (command.getCommand().equals("general")) {
                    coursesCreat.setType((command.getCommand()));
                    System.out.println("department :");
                    creatState++;
                    return true;
                } else if (command.getCommand().equals("professional")) {
                    coursesCreat.setType("professional");
                    System.out.println("department :");
                    creatState++;
                    return true;
                }
                System.out.println("please enter general or professional");
                return true;
            } else if (creatState == 10) {
                for (College college : university.collegesList) {
                    if (college.getName().equals(command.getCommand()) && !DoWEHaveThisCourse(college, coursesCreat)) {
                        coursesCreat.setCollege(college);
                        creatState = 0;
                        college.addListOfCourses(new courses(coursesCreat));
                        coursesCreat.restart();
                        System.out.println("The operation was successful");
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private boolean DoWEHaveThisCourse(College college, courseCreat coursesCreat) {
        for (courses courses : college.getListOfCourses()) {
            if (courses.getCreatCourse().equals(coursesCreat)) {
                return true;
            }
        }
        return false;
    }

    public boolean increaseCapacityCourse(Command command) {
        if (Cli.state.equals("studentCourseList") && creatState == 0) {
            if (command.getCommand().equals("inc")) {
                try {
                    int num = Integer.parseInt(command.getArg());
                    Cli.curentCourse.increaseCapacity(num);
                    System.out.println("The operation was successful");
                    return true;
                } catch (NumberFormatException n) {
                    System.out.println(Application.ERROR);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean courseCreatBreak(Command command) {
        if (command.getCommand().equals("break")) {
            creatState = 0;
            coursesCreat.restart();
            return true;
        }
        return false;
    }

    private boolean courseCreatBack(Command command) {
        if (command.getCommand().equals("back") && command.getArg() == null) {
            if (creatState == 2) {
                System.out.println("name:");
                creatState--;
                return true;
            }
            if (creatState == 3) {
                System.out.println("code:");
                creatState--;
                return true;
            }
            if (creatState == 4) {
                System.out.println("unit:");
                creatState--;
                return true;
            }
            if (creatState == 5) {
                System.out.println("capacity:");
                creatState--;
                return true;
            }
            if (creatState == 6) {
                System.out.println("teacher name:");
                creatState--;
                return true;
            }
            if (creatState == 7) {
                System.out.println("start time(example:-> 09:00) :");
                creatState--;
                return true;
            }
            if (creatState == 8) {
                System.out.println("exam time(example:-> 09:00) :");
                creatState--;
                return true;
            }
            if (creatState == 9) {
                System.out.println("exam date (example:-> 1403/3/30) :");
                creatState--;
                return true;
            }
            if (creatState == 10) {
                System.out.println("type(general or Professional):");
                creatState--;
                return true;
            }
            if (creatState == 11) {
                System.out.println("department :");
                creatState--;
                return true;
            }
        }
        return false;
    }

    private boolean doWeHaveThisStudent(String userName) {
        for (Student student : Cli.curentCourse.getListOfStudent()) {
            if (student.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    private Student whoIsThisStudent(String userName) {
        for (Student stu : university.StudentList) {
            if (stu.getUserName().equals(userName)) {
                return stu;
            }
        }
        return null;
    }

    public boolean deleteStudent(Command command) {
        if (Cli.state.equals("studentCourseList") && command.getCommand().equals("del")) {
            if (doWeHaveThisStudent(command.getArg())) {
                System.out.println(whoIsThisStudent(command.getArg()).DeleteCourse(Cli.curentCourse));
            } else {
                System.out.println("no student with this user name");
            }
            return true;
        }
        return false;
    }

    public boolean StudentPikeCourse(Command command) {
        if (!Cli.AmIAdmin && Cli.state.equals("coursesList")) {
            if (command.getCommand().equals("back")) {
                Cli.state = "studentPanel";
                seeListOfCollege(new Command("ld"));
                return true;
            }
            if (command.getCommand().equals("add")) {
                for (courses cou : Cli.curentCollege.getListOfCourses()) {
                    try {
                        if (Integer.parseInt(command.getArg()) == (cou.getCode())) {
                            System.out.println(Cli.curentStudent.PikeCourses(cou));
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("pleas enter a number");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean StudentSeeOwnPikedCourse(Command command) {
        if (Cli.state.equals("studentPanel")) {
            if (command.getCommand().equals("lpc") && command.getArg() == null) {
                if (Cli.curentStudent.getCoursesArrayList().isEmpty()) {
                    System.out.println("your Picked List is empty");
                    return true;
                }
                System.out.println("Unit picked -> "+Cli.curentStudent.getUnits_piked());
                System.out.println("general Unite ->"+Cli.curentStudent.getGeneral_unit());
                System.out.println("List of your course->");
                System.out.println("NAME  :: code,Unit,teacher,StartTime,examDat,examTime,type,capacity,numOfStu,department");
                for (courses cou : Cli.curentStudent.getCoursesArrayList()) {
                    System.out.println(cou);
                }
                Cli.state = "ownCourse";
                return true;
            }
        }
        return false;
    }

    public boolean DeleteOwnCoursePiked(Command command) {
        if (Cli.state.equals("ownCourse")) {
            if (command.getCommand().equals("back")) {
                Cli.state = "studentPanel";
                return true;
            }
            if (command.getCommand().equals("del") && command.getArg() != null) {
                try {
                    for (courses cou : Cli.curentStudent.getCoursesArrayList()) {
                        if (Integer.parseInt(command.getArg()) == cou.getCode()) {
                            System.out.println(Cli.curentStudent.DeleteCourse(cou));
                            Cli.state = ("studentPanel");
                            StudentSeeOwnPikedCourse(new Command("lpc"));
                            return true;
                        }
                    }
                } catch (NumberFormatException r) {
                    System.out.println("please enter correct input(NumberFormatException)");
                    return true;
                }
                System.out.println("no course found with this username(try a correct course code)");
                return true;
            }
        }
        return false;
    }
}
