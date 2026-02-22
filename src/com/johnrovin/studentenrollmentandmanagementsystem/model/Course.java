package com.johnrovin.studentenrollmentandmanagementsystem.model;

import java.util.ArrayList;

public class Course {

  private final String courseName;

  private ArrayList<Subject> subjects;
  private ArrayList<Student> students;

  public Course(String courseName){
    this.courseName = courseName;
    subjects = new ArrayList<>();
    students = new ArrayList<>();
  }

  public void addSubjects(Subject subject){
    subjects.add(subject);
  }

  public void addStudents(Student student){
    students.add(student);
  }

  public void showSubjects(){
    if(subjects.isEmpty()){
      System.out.println("No Subjects in this Course.");
      return;
    }

    for(Subject subject : subjects){
      System.out.println("- " + subject);
    }
  }

  public void showStudents(){
    if(students.isEmpty()){
      System.out.println("No Student/s in this Course.");
      return;
    }

    for(Student student : students){
      System.out.println("- " + student);
    }
  }

  public boolean hasStudent(){
    return !students.isEmpty();
  }

  public ArrayList<Subject> getSubjects() {
    return subjects;
  }

  @Override
  public String toString(){
    return courseName;
  }
}
