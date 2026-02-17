package com.johnrovin.studentenrollmentandmanagementsystem.model;

public class Student {

  private final String id;
  private final String lastName;
  private final String firstName;
  private final int age;
  private final Course course;
  private Double grade;

  public Student(String id, String lastName, String firstName, int age, Course course) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.age = age;
    this.course = course;
    this.grade = null;
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
  public Double getGrade(){
    return grade;
  }

  @Override
  public String toString() {
    String studentName = lastName + ", " + firstName;
    return """
     ID: %s | Student: %s | Age: %d | Course: %s | Grade: %.2f""".formatted(id, studentName, age, course, grade);
  }
}