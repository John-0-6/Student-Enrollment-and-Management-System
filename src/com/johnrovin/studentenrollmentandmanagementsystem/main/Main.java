package com.johnrovin.studentenrollmentandmanagementsystem.main;

import com.johnrovin.studentenrollmentandmanagementsystem.model.Course;
import com.johnrovin.studentenrollmentandmanagementsystem.model.Student;
import com.johnrovin.studentenrollmentandmanagementsystem.model.Subject;
import com.johnrovin.studentenrollmentandmanagementsystem.repository.CourseRepository;
import com.johnrovin.studentenrollmentandmanagementsystem.repository.StudentRepository;
import com.johnrovin.studentenrollmentandmanagementsystem.service.FileService;
import com.johnrovin.studentenrollmentandmanagementsystem.util.IdGenerator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static void main (String[] args){
    Scanner scanner = new Scanner(System.in);
    StudentRepository studentRepository = new StudentRepository();
    CourseRepository courseRepository = new CourseRepository();

    FileService.loadCourses(courseRepository);
    FileService.loadStudents(studentRepository, courseRepository);

    System.out.println("""
      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      Student Enrollment and Grade System
      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""");

    boolean running = true;
    while(running){
      // Main Menu
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
        // Add Course
        // Instantly add subjects of the course
        case 1:
          try{
            String courseName;
            Course course;
            while(true){
              System.out.print("Enter Course: ");
              courseName = scanner.nextLine().toUpperCase();

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
                subjectName = scanner.nextLine().toUpperCase();

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
            System.out.println("Unexpected Error Occurred.");
            scanner.nextLine();
          }
          break;

        // Enroll a Student
        // Enroll the student in a course
        case 2:
          try{
            if(!courseRepository.hasCourses()){
              System.out.println("Add Course/s First");
              break;
            }
            String studentId = IdGenerator.nextStudentId();
            String lastName;
            while(true){
              System.out.print("Enter Lastname: ");
              lastName = scanner.nextLine().toUpperCase();

              if(lastName.isBlank()){
                System.out.println("Lastname cannot be Empty.\n");
                continue;
              }
              break;
            }

            String firstName;
            while(true){
              System.out.print("Enter Firstname: ");
              firstName = scanner.nextLine().toUpperCase();

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
                System.out.println("Unexpected Error Occurred.");
                scanner.nextLine();
              }
            }

            String selectedCourseName;
            Course selectedCourse;
            while(true){
              System.out.println("""
              ~~~~~~~~~
              Course/s
              ~~~~~~~~~""");
              courseRepository.showCourses();
              System.out.println("");

              System.out.print("Enter Course: ");
              selectedCourseName = scanner.nextLine().toUpperCase();

              if(selectedCourseName.isBlank()){
                System.out.println("Course Name cannot be Empty.\n");
                continue;
              }

              selectedCourse = courseRepository.findCourseByName(selectedCourseName);

              if (selectedCourse == null) {
                System.out.println("Course Not found. Please Add Course/s First.");
                continue;
              }
              break;
            }

            Student student = new Student(studentId, lastName, firstName, age, selectedCourse);

            studentRepository.addStudent(student);

            selectedCourse.addStudents(student);
          }
          catch(Exception e){
            System.out.println("Unexpected Error Occurred.");
            scanner.nextLine();
          }
          break;

        // Assign Grades
        // Choose a course, and then choose a student to edit their grades per subject
        case 3:
          try{
            if(!courseRepository.hasCourses()){
              System.out.println("No Available Course/s.");
              break;
            }

            System.out.println("""
            ~~~~~~~~~
            Course/s
            ~~~~~~~~~""");
            courseRepository.showCourses();
            System.out.println("");

            Course selectedCourseGrade;
            while(true){
              System.out.print("Choose Course: ");
              String selectedCourse = scanner.nextLine().toUpperCase();

              selectedCourseGrade = courseRepository.findCourseByName(selectedCourse);

              if(selectedCourseGrade == null){
                System.out.println("Course Not Found.\n");
                continue;
              }

              if(!selectedCourseGrade.hasStudent()){
                System.out.println("No Students Enrolled in this Course.\n");
                continue;
              }
              break;
            }


            Student selectedStudent;
            while(true){
              System.out.println("""
              ~~~~~~~~~~
              Student/s
              ~~~~~~~~~~""");
              selectedCourseGrade.showStudents();

              System.out.println("\nUse Full Name (Lastname + Firstname)");
              System.out.print("Select Student: ");
              String studentName = scanner.nextLine().toUpperCase();

              selectedStudent = studentRepository.findStudentByName(studentName);

              if(selectedStudent == null){
                System.out.println("Student Not Found.");
                continue;
              }
              break;
            }

            while(true){
              try{
                boolean assigningGrade = true;
                while (assigningGrade) {
                  String answer;
                  while (true) {
                    System.out.println("\nSubjects for " + selectedStudent.getFirstName() + ":");
                    int index = 1;
                    for(Subject subject : selectedStudent.getSubjects()){
                      System.out.println(index++ + ". " + subject);
                    }

                    System.out.print("\nSelect Subject Number to Assign Grade: ");
                    int subjectChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (subjectChoice > selectedStudent.getSubjects().size()) {
                      System.out.println("Invalid Input.\n");
                      continue;
                    }

                    Subject chosenSubject = selectedStudent.getSubjects().get(subjectChoice - 1);

                    System.out.print("Enter Grade for " + chosenSubject + ": ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine();

                    selectedStudent.assignGrade(chosenSubject, grade);

                    System.out.println("Grade Assigned Successfully!\n");

                    while (true) {
                      System.out.print("Assign Another Grade? (y/n): ");
                      answer = scanner.nextLine();

                      if (answer.equalsIgnoreCase("n")) {
                        assigningGrade = false;
                        break;
                      } else if (answer.equalsIgnoreCase("y")) {
                        break;
                      } else {
                        System.out.println("Invalid Choice.\n");
                      }
                    }
                    break;
                  }
                }
                break;
              }
              catch(InputMismatchException e){
                System.out.println("Invalid Input.");
                scanner.nextLine();
              }
              catch(Exception e){
                System.out.println("Unexpected Error Occurred.");
                scanner.nextLine();
              }
            }
          }
          catch(Exception e){
            System.out.println("Unexpected Error Occurred.");
            scanner.nextLine();
          }
          break;

        // View Student Report
        // View specific student by choosing the course first
        // Then select the student with the full name (lastname + firstname)
        case 4:
          try{
            if(!studentRepository.hasStudents()){
              System.out.println("There are no Student/s.");
              break;
            }

            Student student;
            while(true){
              System.out.println("""
              ~~~~~~~~~
              Student/s
              ~~~~~~~~~""");
              studentRepository.showStudents();

              System.out.print("\nEnter Student Full Name (Lastname + FirstName): ");
              String fullName = scanner.nextLine().toUpperCase();
              student = studentRepository.findStudentByName(fullName);

              if(student == null){
                System.out.println("Wrong Format or No Student Found.");
                continue;
              }
              break;
            }

            System.out.println("""
              
              ~~~~~~~~~~~~~~
              Student Report
              ~~~~~~~~~~~~~~""");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getLastName() + ", " + student.getFirstName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Course: " + student.getCourse());
            System.out.println("\nGrades:");
            student.showGrades();
            System.out.println("----------------------\n");
            break;
          }
          catch(Exception e){
            System.out.println("Unexpected Error Occurred.");
            scanner.nextLine();
          }

        // Show all courses that are saved
        // Choose a course to open
        // It will show the subjects of that course, and all the students enrolled in that course
        case 5:
          try{
            if(!courseRepository.hasCourses()){
              System.out.println("There are no Course/s");
              break;
            }
            while(true){
              System.out.println("""
              ~~~~~~~~~
              Course/s
              ~~~~~~~~~""");
              courseRepository.showCourses();
              System.out.println("");

              System.out.println("Press \"Enter\" to exit");
              System.out.print("Choose a Course to open: ");
              String openCourse = scanner.nextLine().toUpperCase();

              if(openCourse.isBlank()){
                break;
              }

              Course selectedCourseOpen = courseRepository.findCourseByName(openCourse);

              if(selectedCourseOpen == null){
                System.out.println("Course not Found.\n");
                continue;
              }
              else{
                System.out.println("Subjects" + " (" + openCourse + ")");
                selectedCourseOpen.showSubjects();
                System.out.println("");
                selectedCourseOpen.showStudents();
              }
              break;
            }
            break;
          }
          catch(Exception e){
            System.out.println("Unexpected Error Occurred.");
            scanner.nextLine();
          }
          break;

        // Show all enrolled students
        case 6:
          System.out.println("""
            ~~~~~~~~~
            Student/s
            ~~~~~~~~~""");
          studentRepository.showStudents();
          break;

        // Exits the program
        case 7:
          System.out.println("----- Exiting Program -----");
          FileService.saveCourses(courseRepository);
          FileService.saveStudents(studentRepository);
          running = false;
          break;
      }
    }

    scanner.close();
  }
}
