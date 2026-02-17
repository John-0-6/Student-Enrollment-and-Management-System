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
- View **student reports** with grades  
- List all **Courses** and their students  
- Input validation for empty fields and invalid choices  

---

## OOP Concepts Used

- Classes & Objects (`Student`, `Course`, `Subject`)  
- Encapsulation (private fields with getters/setters)  
- Repositories (`StudentRepository`, `CourseRepository`) for managing collections  
- Utility classes (`IdGenerator`) for generating unique student IDs  
- Separation of concerns: `Main` handles UI, repositories handle data  

---

## Technologies Used

- Java  
- `Scanner` for user input  
- `ArrayList` for collections  
- `LocalDate` & `DateTimeFormatter` for ID generation  

---

## How It Works

1. **Main Menu** provides options:  
   - Add Course  
   - Add Student  
   - Assign Grade  
   - View Student Report  
   - List All Courses  
   - List All Students  
   - Exit Program  

2. Courses can have multiple **Subjects**.  
3. Students are automatically assigned a **unique ID**.  
4. Students are assigned to **courses** upon creation.  
5. Courses store lists of **enrolled students** and their subjects.  

---

## Usage

1. **Clone the repository:**
```bash
git clone https://github.com/John-0-6/Student-Enrollment-and-Management-System.git
