package Appliction.CLI;

import Appliction.Application;
import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.*;
import Appliction.Panel.Student;
import UserNamePassword.SignIn_UpHandler;
import Appliction.Panel.university;

public class CommandHandler {
    courseCreat coursesCreat = new courseCreat();
    public static int creatState = 0;
    SignIn_UpHandler signInUpHandler = new SignIn_UpHandler();

    public boolean help(Command command) {
        String HelpResult = "";
        if (command.getCommand().equals("help") && command.getArg() == null) {
            if (Cli.state.equals("sign in or sign up")) {
                HelpResult = "signin -> enter to your account\nsignup -> creat an account";
            } else if (Cli.state.equals("sign in")) {
                HelpResult = "back -> back to the sign in or sign up state\nenter a user name and password of your account";
            } else if (Cli.state.equals("sign up")) {
                HelpResult = "back -> back to the sign in or sign up state\nenter a user name and password that you want for your account";
            } else if (Cli.state.equals("adminPanel")) {
                HelpResult = "back -> back to the sign in state\nld -> for see the list of the Department\nmkcou -> creat  new course\nmkdep + name -> creat new department\n";//todo
            } else if (Cli.state.equals("studentPanel")) {
                HelpResult = "back -> back to the sign in\nld -> for see the list of the Department\n";//todo
            } else if (Cli.state.equals("CollegeList")) {
                HelpResult = "back -> back to the Panel state\nname any department -> see the department courses";
            } else if (Cli.state.equals("coursesList")) {
                HelpResult = "back -> back to the departmentList state\ncode of any courses -> see the List of the student of the courses";
            } else if (creatState > 0) {
                HelpResult = "back -> Return to the previous step\n break -> break course creating";
            }
        }
        if (HelpResult.equals("")) {
            return true;
        }
        System.out.println(HelpResult);
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
        if (Cli.state.equals("adminPanel")) {
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
        if (command.getArg() == null) {
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
        if (Cli.state.equals("studentPanel") || Cli.state.equals("adminPanel")) {
            if (command.getArg() == null && command.getCommand().equals("back")) {
                Cli.state = "sign in";
                return true;
            }
            if (command.getArg() == null && command.getCommand().equals("ld")) {
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
        if (Cli.state.equals("CollegeList")) {
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
                    System.out.println("NAME  :: code,Unit,teacher,StartTime,examDat,examtime,type,capacity,numOfStu");
                    for (courses cou : c.getListOfCourses()) {
                        System.out.println(cou);
                    }
                }
            }
            if (!IFindIt && !command.getCommand().equals("help")) {
                System.out.println("no department with this name");
            }
//            } else if (Cli.AmIAdmin) {
//                Cli.state = "coursesList";
//            }
            return true;
        }
        return false;
    }

    public boolean seetheStudent(Command command) {
        if (command.getArg() == null && Cli.state.equals("coursesList")) {
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
        if (Cli.state.equals("studentCourseList")) {
            try {
                if (command.getCommand().equals("add")) {
                    for (Student student : university.StudentList) {
                        if (command.getArg().equals(student.getUserName())) {
                            System.out.println(student.PikeCourses(Cli.curentCourse));
                            return true;
                        }
                    }
                    System.out.println("no student with this username");
                    return true;
                }
            } catch (NullPointerException n) {
                return false;
            }
        }
        return false;
    }

    public boolean deleteStudent(Command command) {
        if (Cli.state.equals("studentCourseList")) {
            try {
                if (command.getCommand().equals("del")) {
                    for (Student student : Cli.curentCourse.ListOfStudent) {
                        if (command.getArg().equals(student.getUserName())) {
                            System.out.println(student.DeleteCourse(Cli.curentCourse));
                            return true;
                        }
                    }
                    System.out.println("no  student with this username");
                    return true;
                }
            } catch (NullPointerException n) {
                return false;
            }
        }
        return false;
    }

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
                System.out.println("Unit:");
                creatState++;
                coursesCreat.setCode(Integer.parseInt(command.getCommand()));
                return true;

            } else if (creatState == 3) {
                System.out.println("capacity:");
                creatState++;
                coursesCreat.setUnit(Integer.parseInt(command.getCommand()));
                return true;

            } else if (creatState == 4) {
                System.out.println("teacher name:");
                creatState++;
                coursesCreat.setCapacity(Integer.parseInt(command.getCommand()));
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
                System.out.println("type(general or Professional):");
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
                    coursesCreat.setType("t");
                    System.out.println("department :");
                    creatState++;
                    return true;
                }
                System.out.println(Application.ERROR);
            } else if (creatState == 10) {
                for (College college : university.collegesList) {
                    if (college.getName().equals(command.getCommand()) && DoWEHaveThisCourse(college, coursesCreat)) {
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
        if (Cli.state.equals("studentCourseList")) {
            if (command.getCommand().equals("capin")) {
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
        if (command.getCommand().equals("back") && command.getArg() != null) {
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
}
