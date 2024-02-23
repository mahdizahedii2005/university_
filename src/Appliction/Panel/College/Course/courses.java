package Appliction.Panel.College.Course;

import Appliction.Panel.College.College;
import Appliction.Panel.Student;
import java.util.ArrayList;

public class courses {
    public ArrayList<Student> ListOfStudent = new ArrayList<>();
    public int numberOfStudent,capacity;
    private final int code;
    private final College College;
    private final String teacher, name, StartTime, examTime, examDate, Type;
    private boolean Available;

    public courses(int numberOfStudent, int capacity, int code, String teacher, String name, String startTime, String examTime, String examDate, String type, boolean available, College college) {
        this.numberOfStudent = numberOfStudent;
        this.capacity = capacity;
        this.code = code;
        this.teacher = teacher;
        this.name = name;
        StartTime = startTime;
        this.examTime = examTime;
        this.examDate = examDate;
        Type = type;
        Available = available;
        this.College = college;
    }
    public void increaseCapacity(int NumIncrease){
        this.capacity = capacity + NumIncrease ;
    }
    public void deleteStudent(Student student){
        ListOfStudent.remove(student);
    }

    public College getCollege() {
        return College;
    }

    public ArrayList<Student> getListOfStudent() {
        return ListOfStudent;
    }

    public void AddListOfStudent(Student student) {
        ListOfStudent.add(student);
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCode() {
        return code;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getExamTime() {
        return examTime;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getType() {
        return Type;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }
}
