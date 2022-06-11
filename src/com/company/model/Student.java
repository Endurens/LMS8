package com.company.model;

import com.company.repository.StudentRepository;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Student {
    private int id;
    String name;
    String surname;

    private static int lastId;
    public static DefaultTableModel model = new DefaultTableModel();
    public static ArrayList<Student> allStudents = new ArrayList<>();

    public Student(String name, String surname) {
        setProperties(++lastId, name, surname);
        StudentRepository.add(id, name, surname);

    }

    public Student(int id, String name, String surname) {
        lastId = id;
        setProperties(id, name, surname);
    }

    public static void update(int id, String name, String surname) {
        StudentRepository.update(id, name, surname);
    }

    public static void delete(int id, int rowIndex) {
        StudentRepository.delete(id);
        model.removeRow(rowIndex);
    }

    public static ArrayList<Course> getCoursesById(int id) {
        return Enrollment.getCoursesByStudentId(id);
    }

    public void setProperties(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        allStudents.add(this);
        model.addRow(new Object[]{id, name, surname});
    }

    public int getId() {
        return this.id;
    }

    public String getInfo() {
        return this.id + " " + this.name + " " + this.surname;
    }

    public ArrayList<Course> getCourses(boolean toEnroll) {
        if (toEnroll)
            return Course.getCoursesToEnrollByStudent(this);
        else
            return Enrollment.getCoursesByStudent(this);
    }
    public ArrayList<Integer> getMarks() {
        ArrayList<Integer> marks = new ArrayList<>();
        for (Course course : this.getCourses(false)) {
            marks.add(AcademicPerformance.getMarkByStudentAndCourse(this, course));
        }
        return marks;
    }

    public static Student getStudentById(int id) {
        for (Student student : allStudents) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public String toString() {
        return this.id + " " + this.name;
    }

    public String getName() {

        return name;
    }

    public String getSurname() {
        return surname;
    }
}

