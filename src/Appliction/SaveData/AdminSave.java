package Appliction.SaveData;

import Appliction.Panel.College.College;
import Appliction.Panel.College.Course.courses;
import Appliction.Panel.Student;
import Appliction.Panel.university;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminSave {
    private void printCourses(File file, College col, PrintWriter printWriter) {
        for (courses cou : col.getListOfCourses()) {
            printWriter.println(cou);
        }
        printWriter.println("#");
    }

    private void printDepartment(File fille) {
        try {
            File file1 = new File(fille.getAbsolutePath() + "\\department");
            file1.mkdirs();
            File file = new File(file1.getAbsolutePath() + "\\departments.txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter, true);
            for (College col : university.collegesList) {
                printWriter.println("!" + col);
                printCourses(file, col,printWriter);
            }
        } catch (IOException e) {

        }
    }

    private void printListOfStudent(File file, String addr) {
        for (Student student : university.StudentList) {
            StudentSave.save_Student(student);
        }
    }

    public void Export(String addr) {
        File file = new File(addr);
        file.mkdirs();
        if (file.exists()) {
            printDepartment(file);
            printListOfStudent(file, addr);
        } else {
            System.out.println("cant find the file in this address");
        }
    }
}
