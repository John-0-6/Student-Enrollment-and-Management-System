package com.johnrovin.studentenrollmentandmanagementsystem.repository;

import com.johnrovin.studentenrollmentandmanagementsystem.model.Course;

import java.util.ArrayList;

public class CourseRepository {

  ArrayList<Course> courses;

  public CourseRepository(){
    courses = new ArrayList<>();
  }

  public void addCourses(Course course){
    courses.add(course);
  }

  public void showCourses(){
    if(courses.isEmpty()){
      System.out.println("There are no Course/s\n");
      return;
    }

    for(Course course : courses){
      System.out.println(course);
    }
  }

  public Course findCourseByName(String name) {
    for (Course course : courses) {
      if (course.toString().equalsIgnoreCase(name)) {
        return course;
      }
    }
    return null;
  }
}