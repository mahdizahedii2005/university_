package Appliction.Panel.College.Course;

import Appliction.Panel.College.College;
import Appliction.Panel.Student;

import java.util.ArrayList;

public class courses {
    public ArrayList<Student> ListOfStudent = new ArrayList<>();
    public int numberOfStudent, capacity;
    private final int code, Unit;
    private final College College;
    private final String teacher, name, StartTime, examTime, examDate, Type;
    private boolean Available;

    public courses(courseCreat courseCreat) {
        this.numberOfStudent = 0;
        this.capacity = courseCreat.getCapacity();
        this.code = courseCreat.getCode();
        this.Unit = courseCreat.getUnit();
        this.teacher = courseCreat.getTeacher();
        this.name = courseCreat.getName();
        StartTime = courseCreat.getStartTime();
        this.examTime = courseCreat.getExamTime();
        this.examDate = courseCreat.getExamDate();
        Type = courseCreat.getType();
        Available = true;
        this.College = courseCreat.getCollege();
    }
    public courseCreat getCreatCourse(){
        return new courseCreat(numberOfStudent,capacity,code,Unit,College,teacher,name,StartTime,examTime,examDate,Type,Available);
    }

    public courses(int capacity, int code, int Unit, String teacher, String name, String startTime, String examTime, String examDate, String type, boolean available, College college) {
        this.numberOfStudent = 0;
        this.capacity = capacity;
        this.code = code;
        this.Unit = Unit;
        this.teacher = teacher;
        this.name = name;
        StartTime = startTime;
        this.examTime = examTime;
        this.examDate = examDate;
        Type = type;
        Available = available;
        this.College = college;
    }

    @Override
    public String toString() {
        return name + " :: " + code + " , " + Unit + " , " + teacher + " , " + StartTime + " , " + examDate + " , " + examTime + " , " + Type + " , " + capacity + " , " + numberOfStudent;

    }

    public void increaseNumStudent() {
        this.numberOfStudent++;
    }

    public void DecreaseNumStudent() {
        this.numberOfStudent--;
    }

    public void increaseCapacity(int NumIncrease) {
        this.capacity = capacity + NumIncrease;
        increaseNumStudent();
    }

    public void deleteStudent(Student student) {
        ListOfStudent.remove(student);
        DecreaseNumStudent();
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

    public int getUnit() {
        return Unit;
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
