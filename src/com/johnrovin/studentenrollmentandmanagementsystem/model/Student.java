package com.johnrovin.studentenrollmentandmanagementsystem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {

  private final String id;
  private final String lastName;
  private final String firstName;
  private final int age;
  private final Course course;
  private Double grade;
  private Map<Subject, Double> grades;

  ArrayList<Subject> subjects;

  public Student(String id, String lastName, String firstName, int age, Course course){
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.age = age;
    this.course = course;
    this.grade = null;
    subjects = new ArrayList<>(course.getSubjects());
    this.grades = new HashMap<>();
    for (Subject subject : subjects) {
      grades.put(subject, null);
    }
  }

  public void assignGrade(Subject subject, double grade){
    if(grades.containsKey(subject)){
      grades.put(subject, grade);
    }
  }

  public void showGrades(){
    for(Subject subject : subjects){
      Double grade = grades.get(subject);
      System.out.println(subject + " : " + (grade == null ? "Not graded yet" : grade));
    }
  }

  public ArrayList<Subject> getSubjects() {
    return subjects;
  }

  // Getters
  public String getId(){
    return id;
  }
  public String getLastName(){
    return lastName;
  }
  public String getFirstName(){
    return firstName;
  }
  public int getAge(){
    return age;
  }
  public Course getCourse(){
    return course;
  }
  public Map<Subject, Double> getGrades(){
    return grades;
  }

  @Override
  public String toString() {
    return """
     ID: %s | Student: %s, %s | Age: %d | Course: %s""".formatted(id, lastName, firstName, age, course);
  }
}
