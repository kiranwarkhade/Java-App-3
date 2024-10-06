package school_management_application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchoolManagement {

    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;
    private Scanner scanner;

    public SchoolManagement() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(name));
        System.out.println("Student added: " + name);
    }

    public void addTeacher() {
        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();
        teachers.add(new Teacher(name));
        System.out.println("Teacher added: " + name);
    }

    public void addCourse() {
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        courses.add(new Course(name));
        System.out.println("Course added: " + name);
    }

    public void enrollStudent() {
        System.out.print("Enter student name to enroll: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter course name to enroll in: ");
        String courseName = scanner.nextLine();
        
        Course course = findCourse(courseName);
        if (course != null) {
            course.enrollStudent(studentName);
        } else {
            System.out.println("Course not found.");
        }
    }

    public void displayStudents() {
        System.out.println("Students:");
        for (Student student : students) {
            System.out.println("- " + student.getName());
        }
    }

    public void displayTeachers() {
        System.out.println("Teachers:");
        for (Teacher teacher : teachers) {
            System.out.println("- " + teacher.getName());
        }
    }

    public void displayCourses() {
        System.out.println("Courses:");
        for (Course course : courses) {
            System.out.println("- " + course.getName() + " (Enrolled: " + course.getEnrolledStudents() + ")");
        }
    }

    public void displayStudentCourses() {
        System.out.print("Enter student name to display courses: ");
        String studentName = scanner.nextLine();
        System.out.println("Courses for " + studentName + ":");
        for (Course course : courses) {
            if (course.isStudentEnrolled(studentName)) {
                System.out.println("- " + course.getName());
            }
        }
    }

    public void removeStudent() {
        System.out.print("Enter student name to remove: ");
        String name = scanner.nextLine();
        students.removeIf(student -> student.getName().equalsIgnoreCase(name));
        System.out.println("Student removed: " + name);
    }

    public void userInput() {
        String command;
        do {
            System.out.println("\nCommands: addStudent, addTeacher, addCourse, enrollStudent, displayStudents, displayTeachers, displayCourses, displayStudentCourses, removeStudent, exit");
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "addstudent":
                    addStudent();
                    break;
                case "addteacher":
                    addTeacher();
                    break;
                case "addcourse":
                    addCourse();
                    break;
                case "enrollstudent":
                    enrollStudent();
                    break;
                case "displaystudents":
                    displayStudents();
                    break;
                case "displayteachers":
                    displayTeachers();
                    break;
                case "displaycourses":
                    displayCourses();
                    break;
                case "displaystudentcourses":
                    displayStudentCourses();
                    break;
                case "removestudent":
                    removeStudent();
                    break;
                case "exit":
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        } while (!command.equalsIgnoreCase("exit"));
        scanner.close();
    }

    public Course findCourse(String name) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(name)) {
                return course;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SchoolManagement schoolManagement = new SchoolManagement();
        schoolManagement.userInput();
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Teacher {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Course {
    private String name;
    private List<String> enrolledStudents;

    public Course(String name) {
        this.name = name;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void enrollStudent(String studentName) {
        enrolledStudents.add(studentName);
        System.out.println("Student " + studentName + " enrolled in " + name);
    }

    public int getEnrolledStudents() {
        return enrolledStudents.size();
    }

    public boolean isStudentEnrolled(String studentName) {
        return enrolledStudents.contains(studentName);
    }
}
