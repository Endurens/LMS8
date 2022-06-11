package com.company;


import com.company.repository.CourseRepository;
import com.company.repository.EnrollmentRepository;
import com.company.repository.StudentRepository;
import com.company.view.MainFrame;
import com.company.view.course.AddCourseFrame;
import com.company.view.student.AddStudentFrame;

public class Main {

    public static MainFrame mainFrame = new MainFrame();
    public static AddStudentFrame addStudentFrame = new AddStudentFrame();
    public static AddCourseFrame addCourseFrame = new AddCourseFrame();

    public static void main(String[] args) {
        StudentRepository.getAll();
        CourseRepository.getAll();
        EnrollmentRepository.getAll();
    }
}

