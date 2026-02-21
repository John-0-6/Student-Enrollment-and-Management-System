# Student-Enrollment-and-Management-System
A console-based Student Enrollment and Grade System built in Java. This system allows managing courses, subjects, students, and assigning grades. It supports viewing student reports and listing all courses with enrolled students.

---

Work in Progress: This project is a console-based Student Enrollment and Grade System. The current focus is on:

Refactoring with helper methods to reduce repetitive code and improve readability.

Adding File I/O to enable persistent storage of courses, students, and subjects between program runs.

## Features

- Add new **Courses** and **Subjects**  
- Add new **Students** with auto-generated IDs  
- Assign students to **Courses**  
- **Assign grades** to students per subject ✅ *(New feature)*  
- View **student reports** with grades ✅ *(New feature)*  
- List all **Courses** and enrolled students  
- Input validation for empty fields and invalid choices  

---

## OOP Concepts Used

- Classes & Objects (`Student`, `Course`, `Subject`)  
- Encapsulation (private fields with getters/setters)  
- Repositories (`StudentRepository`, `CourseRepository`) for managing collections  
- Utility classes (`IdGenerator`) for generating unique student IDs  
- Separation of concerns: `Main` handles UI, repositories handle data  
- Collections: `ArrayList` for storing courses, students, and subjects  
- Maps: `HashMap` for storing grades per subject  

---

## Technologies Used

- Java  
- `Scanner` for console input  
- `ArrayList` and `HashMap` for collections  
- `LocalDate` & `DateTimeFormatter` for ID generation  

---

## How It Works

1. **Main Menu** provides options:  
   1. Add Course  
   2. Add Student  
   3. Assign Grade ✅  
   4. View Student Report ✅  
   5. List All Courses  
   6. List All Students  
   7. Exit Program  

2. Courses can have multiple **Subjects**.  
3. Students are automatically assigned a **unique ID**.  
4. Students are assigned to **courses** upon creation.  
5. **Grades** can be assigned per subject for each student.  
6. **Student reports** show all subjects and assigned grades.  

---

## Usage

1. **Clone the repository:**
```bash
git clone https://github.com/John-0-6/Student-Enrollment-System.git
