package com.johnrovin.studentenrollmentandmanagementsystem.repository;

import com.johnrovin.studentenrollmentandmanagementsystem.model.Student;

import java.util.ArrayList;

public class StudentRepository {

  private ArrayList<Student> students;

  public StudentRepository(){
    students = new ArrayList<>();
  }

  public void addStudent(Student student){
    students.add(student);
  }

  public void showStudents(){
    if(students.isEmpty()){
      System.out.println("There are no Student/s\n");
      return;
    }

    for(Student student : students){
      System.out.println(student);
    }
  }
}