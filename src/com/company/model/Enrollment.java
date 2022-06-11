package com.company.model;

import com.company.repository.EnrollmentRepository;

import java.util.ArrayList;

public class Enrollment {
    int id;
    Student student;
    Course course;

    static int lastID;
    static ArrayList<Enrollment> allCE = new ArrayList<>();

    public Enrollment(Student student, Course course) {
        this.id = ++lastID;
        this.student = student;
        this.course = course;
        allCE.add(this);
        EnrollmentRepository.add(id, student.getId(), course.getId());
    }

    public Enrollment(int id, int studentId, int courseId) {
        this.id = lastID = id;
        this.student = Student.getStudentById(studentId);
        this.course = Course.getCourseById(courseId);
        allCE.add(this);
    }

    public static ArrayList<Student> getStudentsByCourse(Course course) {
        ArrayList<Student> res = new ArrayList<>();
        for (Enrollment ce : allCE) {
            if (course.getId() == ce.course.getId()) {
                res.add(ce.student);
            }
        }
        return res;
    }

    public static ArrayList<Course> getCoursesByStudent(Student student) {
        ArrayList<Course> res = new ArrayList<>();
        for (Enrollment ce : allCE) {
            if (student.getId() == ce.student.getId()) {
                res.add(ce.course);
            }
        }
        return res;
    }

    public static Enrollment getCourseEnrollment(Student st, Course c) {
        for (Enrollment ce : allCE) {
            if (ce.student.getId() == st.getId() && ce.course.getId() == c.getId()) {
                return ce;
            }
        }
        return new Enrollment(st, c);
    }

    public static ArrayList<Course> getCoursesByStudentId(int id) {

        ArrayList<Course> res = new ArrayList<>();
        for (Enrollment ce : allCE) {
            if (id == ce.student.getId()) {
                res.add(ce.course);
            }
        }
        return res;
    }

    public int getId() {
        return id;
    }
}
