package com.johnrovin.studentenrollmentandmanagementsystem.service;

import com.johnrovin.studentenrollmentandmanagementsystem.model.Course;
import com.johnrovin.studentenrollmentandmanagementsystem.model.Student;
import com.johnrovin.studentenrollmentandmanagementsystem.model.Subject;
import com.johnrovin.studentenrollmentandmanagementsystem.repository.CourseRepository;
import com.johnrovin.studentenrollmentandmanagementsystem.repository.StudentRepository;

import java.io.*;

public class FileService {

  private static final String COURSE_FILE = "courses-data.txt";
  private static final String STUDENT_FILE = "students-data.txt";

  // SAVE COURSES
  public static void saveCourses(CourseRepository courseRepository) {
    try(PrintWriter writer = new PrintWriter(new FileWriter(COURSE_FILE))){

      for(Course course : courseRepository.getCourses()){
        StringBuilder subjects = new StringBuilder();
        for (Subject subject : course.getSubjects()) {
          subjects.append(subject.toString()).append(",");
        }

        if (subjects.length() > 0)
          subjects.deleteCharAt(subjects.length() - 1);

        writer.println(course.toString() + "|" + subjects);
      }
    }
    catch(IOException e){
      System.out.println("Error saving courses.");
    }
  }

  // LOAD COURSES
  public static void loadCourses(CourseRepository courseRepository) {
    File file = new File(COURSE_FILE);
    if(!file.exists()){
      return;
    }

    try(BufferedReader reader = new BufferedReader(new FileReader(file))){

      String line;
      while((line = reader.readLine()) != null){
        String[] parts = line.split("\\|");

        String courseName = parts[0];
        Course course = new Course(courseName);

        if(parts.length > 1){
          String[] subjectNames = parts[1].split(",");

          for(String subjectName : subjectNames){
            course.addSubjects(new Subject(subjectName));
          }
        }
        courseRepository.addCourses(course);
      }

    }
    catch(IOException e){
      System.out.println("Error Loading Courses.");
    }
  }

  // SAVE STUDENTS
  public static void saveStudents(StudentRepository studentRepository) {
    try(PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE))){

      for(Student student : studentRepository.getStudents()){
        StringBuilder gradeBuilder = new StringBuilder();

        for(Subject subject : student.getSubjects()){
          Double grade = student.getGrades().get(subject);
          gradeBuilder.append(subject.toString()).append(":").append(grade == null ? "null" : grade).append(",");
        }

        if(gradeBuilder.length() > 0)
          gradeBuilder.deleteCharAt(gradeBuilder.length() - 1);

        writer.println(
          student.getId() + "|" +
          student.getLastName() + "|" +
          student.getFirstName() + "|" +
          student.getAge() + "|" +
          student.getCourse().toString() + "|" +
          gradeBuilder
        );
      }
    }
    catch(IOException e){
      System.out.println("Error Saving Students.");
    }
  }

  // LOAD STUDENTS
  public static void loadStudents(StudentRepository studentRepository, CourseRepository courseRepository) {
    File file = new File(STUDENT_FILE);
    if (!file.exists()){
      return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

      String line;
      while((line = reader.readLine()) != null){
        String[] parts = line.split("\\|");

        String id = parts[0];
        String lastName = parts[1];
        String firstName = parts[2];
        int age = Integer.parseInt(parts[3]);
        String courseName = parts[4];

        Course course = courseRepository.findCourseByName(courseName);
        if(course == null){
          continue;
        }

        Student student = new Student(id, lastName, firstName, age, course);

        if(parts.length > 5){
          String[] grades = parts[5].split(",");

          for(String gradePair : grades){
            String[] gradeData = gradePair.split(":");
            String subjectName = gradeData[0];
            String gradeValue = gradeData[1];

            for(Subject subject : student.getSubjects()){
              if(subject.toString().equals(subjectName)){
                if(!gradeValue.equals("null"))
                  student.assignGrade(subject, Double.parseDouble(gradeValue));
              }
            }
          }
        }

        studentRepository.addStudent(student);
        course.addStudents(student);
      }
    }
    catch(IOException e){
      System.out.println("Error Loading Students.");
    }
  }
}