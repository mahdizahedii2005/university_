package Appliction.SaveData;

import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.courses;
import Appliction.Panel.Student;
import Appliction.Panel.university;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StudentSave {
    public static void save_Student(Student student) {
        File file = new File("src\\Appliction\\saveData");
        save_Student(student, file);
    }

    public static void save_Student(Student student, File file) {
        File ni = new File(file.getAbsolutePath() + "\\StudentFile");
        ni.mkdir();
        File Fi = new File(ni.getAbsolutePath() +"\\"+ student.getUserName() + ".txt");
        try {
            if (!Fi.exists()) {
                if (!Fi.createNewFile()) {
                    System.out.println("icant handle the save");
                }
            }
            FileWriter Print_auto_save_file;
            PrintWriter print_auto_save;

            Print_auto_save_file = new FileWriter(Fi);
            print_auto_save = new PrintWriter(Print_auto_save_file, true);
            for (courses cou : student.getCoursesArrayList()) {
                print_auto_save.println(cou.fileprint());
            }
            Print_auto_save_file.close();
            print_auto_save.close();
        } catch (IOException I) {
            System.out.println("i have problem");
        }
    }

    public static void loud_Student(Student student) {
        File Fi = new File("src\\Appliction\\saveData\\StudentFile\\" + student.getUserName() + ".txt");
        if (Fi.exists()) {
            try {
                Scanner sc = new Scanner(Fi);
                student.restart();
                while (sc.hasNextLine()) {
                    scancourse(sc.nextLine(), student);
                }
            } catch (IOException I) {
                return;
            }
        }
    }

    private static void scancourse(String coursData, Student student) {
        String[] data = coursData.split(":");
        for (College col : university.collegesList) {
            if (col.getName().equals(data[data.length - 1])) {
                for (courses cou : col.getListOfCourses()) {
                    if (cou.getCode() == Integer.parseInt(data[0])) {
                        student.PikeCourses(cou);
                    }
                }
            }
        }
    }
}
