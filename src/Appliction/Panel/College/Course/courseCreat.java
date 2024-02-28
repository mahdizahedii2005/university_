package Appliction.Panel.College.Course;

import Appliction.Panel.College.College;

public class courseCreat {
    public int numberOfStudent = 0, capacity;
    private int code, Unit;
    private Appliction.Panel.College.College College;
    private String teacher, name, StartTime, examTime, examDate, Type;
    private boolean Available = true;

    public courseCreat() {
    }
    public courseCreat(int numberOfStudent, int capacity, int code, int unit, Appliction.Panel.College.College college, String teacher, String name, String startTime, String examTime, String examDate, String type, boolean available) {
        this.numberOfStudent = numberOfStudent;
        this.capacity = capacity;
        this.code = code;
        Unit = unit;
        College = college;
        this.teacher = teacher;
        this.name = name;
        StartTime = startTime;
        this.examTime = examTime;
        this.examDate = examDate;
        Type = type;
        Available = available;
    }

    public void restart() {
        numberOfStudent = 0;
        capacity = 0;
        code = 0;
        Unit = 0;
        teacher = null;
        name = null;
        StartTime = null;
        examTime = null;
        examDate = null;
        Type = null;
        College = null;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getUnit() {
        return Unit;
    }

    public void setUnit(int unit) {
        Unit = unit;
    }

    public Appliction.Panel.College.College getCollege() {
        return College;
    }

    public void setCollege(Appliction.Panel.College.College college) {
        College = college;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
