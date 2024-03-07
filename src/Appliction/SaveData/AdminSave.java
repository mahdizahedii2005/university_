package Appliction.SaveData;

import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.courses;
import Appliction.Panel.Student;
import Appliction.Panel.university;
import UserNamePassword.SignIn_UpHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminSave {
    private void printCourses(College col, PrintWriter printWriter) {
        for (courses cou : col.getListOfCourses()) {
            printWriter.println(cou.printAlldatail());
        }
        printWriter.println("#");
    }

    private void printDepartment(File fille) {
        try {
            File file = new File(fille.getAbsolutePath() + "\\departments.txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter, true);
            for (College col : university.collegesList) {
                printWriter.println("!" + col);
                printCourses(col, printWriter);
            }
        } catch (IOException e) {

        }
    }

    public void printListOfStudent(File file) {
        for (Student student : university.StudentList) {
            StudentSave.save_Student(student, file);
        }
    }

    public void Export(String addr) {
        File file1 = new File(addr);
        File file = new File(file1.getAbsolutePath() + "\\saves");
        file.mkdirs();
        if (file.exists()) {
            printDepartment(file);
            printListOfStudent(file);
        } else {
            System.out.println("cant find the file in this address");
        }
    }

    private void importDepDatail(File file) {
        try {
            Scanner sc = new Scanner(file);
            boolean Startgiri = false;
            College college = null;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.charAt(0) == '#') {
                    Startgiri = false;
                    college = null;
                } else if (line.charAt(0) == '!' && !Startgiri) {
                    Startgiri = true;
                    line = line.substring(1);
                    College college1 = new College(line);
                    college = college1;
                    university.collegesList.add(college);
                } else if (Startgiri) {
                    String[] vorodi = line.split(",");
                    college.addListOfCourses(new courses(vorodi, college));

                }

            }
        } catch (IOException t) {
        }
    }

    private ArrayList<File> reListOfFile(File file) {
        ArrayList<File> List = new ArrayList<>();
        for (String path : file.list()) {
            List.add(new File(file.getAbsolutePath() + "\\" + path));
        }
        return List;
    }

    private String WhatIsThePass(String username) {
        SignIn_UpHandler signInUpHandler = new SignIn_UpHandler();
        return signInUpHandler.GiveMEPass(username);
    }

    private String[] nameAndPass(String path) {
        int end = -1;
        int first = -1;
        for (int i = path.length() - 1; true; i--) {
            if (path.charAt(i) == '.') {
                end = i;
                continue;
            }
            if (path.charAt(i) == '\\') {
                first = i + 1;
                break;
            }
        }
        String username = path.substring(first, end);
        return new String[]{username, WhatIsThePass(username)};
    }

    private College findCollegeByName(String name) {
        for (College col : university.collegesList) {
            if (col.getName().equals(name)) {
                return col;
            }
        }
        return null;
    }

    private courses findCourseByCode(int code, College college) {
        for (courses c : college.getListOfCourses()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    private void importStudentDatail(File file) {
        for (File filee : reListOfFile(file)) {
            try {
                Scanner sc = new Scanner(filee);
                String[] pas = nameAndPass(filee.getPath());
                Student student = new Student(pas[0], pas[1]);
                university.StudentList.add(student);
                while (sc.hasNextLine()) {
                    String[] codeCol = sc.nextLine().split(":");
                    College mored_nazar = findCollegeByName(codeCol[1]);
                    try {
                        student.PikeCourses(findCourseByCode(Integer.parseInt(codeCol[0]), mored_nazar));
                    } catch (NullPointerException n) {
                        System.out.println("the course that picked by student and available course in the university don't matched each other");
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    public void Import(String addr) {
        File StudentFile = new File(addr + "\\saves\\StudentFile");
        File departments = new File(addr + "\\saves\\departments.txt");
        if (departments.exists()) {
            university.restart();
            importDepDatail(departments);
        } else {
            System.out.println("cant find dep file(its not the file that you export try the file that export with this platform");

        }
        if (StudentFile.exists()) {
            importStudentDatail(StudentFile);
        } else {
            System.out.println("cant find student file(its not the file that you export try the file that export with this platform");
        }
    }
}
