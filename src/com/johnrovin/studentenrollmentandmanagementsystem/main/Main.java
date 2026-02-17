package com.johnrovin.studentenrollmentandmanagementsystem.main;

import com.johnrovin.studentenrollmentandmanagementsystem.model.Course;
import com.johnrovin.studentenrollmentandmanagementsystem.model.Student;
import com.johnrovin.studentenrollmentandmanagementsystem.model.Subject;
import com.johnrovin.studentenrollmentandmanagementsystem.repository.CourseRepository;
import com.johnrovin.studentenrollmentandmanagementsystem.repository.StudentRepository;
import com.johnrovin.studentenrollmentandmanagementsystem.util.IdGenerator;

import java.util.Scanner;

public class Main {
  public static void main (String[] args){
    Scanner scanner = new Scanner(System.in);
    StudentRepository studentRepository = new StudentRepository();
    CourseRepository courseRepository = new CourseRepository();

    System.out.println("""
      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      Student Enrollment and Grade System
      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""");

    boolean running = true;
    while(running){

      System.out.println("\n1. Add Course");
      System.out.println("2. Add Student");
      System.out.println("3. Assign Grade");
      System.out.println("4. View Student Report");
      System.out.println("5. List All Courses");
      System.out.println("6. List All Student");
      System.out.println("7. Exit Program");

      int choice;
      while(true){
        System.out.print("Enter your Choice: ");
        choice = scanner.nextInt();
        scanner.nextLine();

        if(choice < 1 || choice > 7){
          System.out.println("Invalid Choice.");
          continue;
        }
        break;
      }

      switch(choice){
        case 1:
          try{
            String courseName;
            Course course;
            while(true){
              System.out.print("Enter Course: ");
              courseName = scanner.nextLine();

              if(courseName.isBlank()){
                System.out.println("Course cannot be Empty.\n");
                continue;
              }
              course = new Course(courseName);

              courseRepository.addCourses(course);
              break;
            }

            boolean addingSubjects = true;
            while (addingSubjects) {
              String subjectName;
              String answer;
              while(true){
                System.out.print("Enter Subject Name: ");
                subjectName = scanner.nextLine();

                if(subjectName.isBlank()){
                  System.out.println("Subject cannot be Empty.\n");
                  continue;
                }

                while(true){
                  System.out.print("Add another subject? (y/n): ");
                  answer = scanner.nextLine();

                  if(answer.equalsIgnoreCase("n")){
                    addingSubjects = false;
                    break;
                  }
                  else if(answer.equalsIgnoreCase("y")){
                    break;
                  }
                  else{
                    System.out.println("Invalid Choice.\n");
                  }
                }
                break;
              }

              Subject subject = new Subject(subjectName);
              course.addSubjects(subject);

            }
          }
          catch(Exception e){
            System.out.println("Unexpected error occurred.\n");
          }
          break;

        case 2:
          try{
            String studentId = IdGenerator.nextStudentId();
            String lastName;
            while(true){
              System.out.print("Enter Lastname: ");
              lastName = scanner.nextLine();

              if(lastName.isBlank()){
                System.out.println("Lastname cannot be Empty.\n");
                continue;
              }
              break;
            }

            String firstName;
            while(true){
              System.out.print("Enter Firstname: ");
              firstName = scanner.nextLine();

              if(firstName.isBlank()){
                System.out.println("Firstname cannot be Empty.\n");
                continue;
              }
              break;
            }

            int age;
            while(true){
              try{
                System.out.print("Enter Age: ");
                age = scanner.nextInt();
                scanner.nextLine();

                if(age <= 0){
                  System.out.println("Age cannot be less than or equal to 0.\n");
                  continue;
                }
                break;
              }
              catch(Exception e){
                System.out.println("Error");
              }
            }

            // Show available courses
            courseRepository.showCourses();
            System.out.println("");

            String selectedCourseName;
            Course selectedCourse;
            while(true){
              System.out.print("Enter Course Name: ");
              selectedCourseName = scanner.nextLine();

              if(selectedCourseName.isBlank()){
                System.out.println("Course name cannot be Empty.\n");
                continue;
              }

              selectedCourse = courseRepository.findCourseByName(selectedCourseName);

              if (selectedCourse == null) {
                System.out.println("Course not found. Please add course first.");
                continue;
              }
              break;
            }

            Student student = new Student(studentId, lastName, firstName, age, selectedCourse);

            studentRepository.addStudent(student);

            selectedCourse.addStudents(student);
          }
          catch(Exception e){
            System.out.println("Error.");
            scanner.nextLine();
          }
          break;

        case 5:
          while(true){
            System.out.println("""
            ~~~~~~~~~
            Course/s:
            ~~~~~~~~~""");
            courseRepository.showCourses();
            System.out.println("");

            System.out.print("Choose a Course to open: ");
            String openCourse = scanner.nextLine();

            Course selectedCourseOpen = courseRepository.findCourseByName(openCourse);

            if(selectedCourseOpen == null){
              System.out.println("Course not Found.\n");
              continue;
            }
            else{
              System.out.println("Subjects under " + openCourse + ":");
              selectedCourseOpen.showSubjects();
              System.out.println("");
              selectedCourseOpen.showStudents();
            }
            break;
          }
          break;

        case 6:
          studentRepository.showStudents();
          break;

        case 7:
          running = false;
          break;
      }
    }

    scanner.close();
  }
}