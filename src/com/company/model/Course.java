package com.company.model;

import com.company.repository.CourseRepository;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Course {

    private int id;
    String title;
    String description;

    private static int lastId;
    public static DefaultTableModel model = new DefaultTableModel();
    public static ArrayList<Course> allCourses = new ArrayList<>();


    public Course(String title, String description) {
        setProperties(++lastId, title, description);
        CourseRepository.addCourse(id, title, description);

    }

    public Course(int id, String title, String description){
        lastId = id;
        setProperties(id, title, description);
    }

    public static ArrayList<Course> getCoursesToEnrollByStudent(Student student) {
        ArrayList<Course> courses =  new ArrayList<>();
        for (Course course : allCourses){
            if (!Enrollment.getCoursesByStudent(student).contains(course)){
                courses.add(course);
            }
        }
        return courses;
    }

    public void setProperties(int id, String title, String description){

        this.id = id;
        this.title = title;
        this.description = description;
        allCourses.add(this);
        model.addRow(new Object[]{this.id, title, description});
    }

    public static void update(int id, String title, String description) {
        CourseRepository.update(id, title, description);
    }

    public static void delete(int id, int rowIndex) {
        CourseRepository.delete(id);
        model.removeRow(rowIndex);
    }


    public String getInfo() {
        return this.id + " " + this.title + "\n" + this.description;
    }


    public int getId() {
        return id;
    }

    public ArrayList<Student> getStudents() {
        return Enrollment.getStudentsByCourse(this);
    }

    public String toString() {
        return this.id + " " + this.title;
    }

    public static Course getCourseById(int id){
        for (Course course: allCourses){
            if (course.getId() == id){
                return course;
            }
        }
        return null;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

