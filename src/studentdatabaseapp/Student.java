package studentdatabaseapp;

import java.util.Scanner;

public class Student {
    public final String firstName;
    public final String lastName;
    public final int gradeYear;
    public String studentID;
    private String courses = "";
    private int tuitionBalance;
    private static int id = 1000;

    // Constructor: prompt user to enter student's name and year
    public Student() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter students first name: ");
        this.firstName = in.nextLine();

        System.out.println("Enter students last name: ");
        this.lastName = in.nextLine();

        System.out.println("1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior\nEnter student class level: ");
        this.gradeYear = in.nextInt();

        setStudentID();
    }

    // Generate ID
    private void setStudentID() {
        // Grade Level + ID
        id++;
        this.studentID = gradeYear + "" + id;
    }

    // Enroll in courses
    public void enroll() {
        // Loop until user quits
        while (true) {
            System.out.print("Enter a course to enroll (Q to quit): ");
            Scanner in = new Scanner(System.in);

            // Get input from user
            String course = in.nextLine();
            if (!course.equals("Q")) {
                courses += "\n  " + course;
                int costOfCourse = 600;
                tuitionBalance += costOfCourse;
            } else {
                break;
            }
        }
    }

    // View balance
    public void viewBalance() {
        System.out.println("Your balance is: $" + tuitionBalance);
    }

    // Make a payment
    public void payTuition() {
        System.out.print("Enter your payment: $");
        Scanner in = new Scanner(System.in);
        int payment = in.nextInt();
        tuitionBalance = tuitionBalance - payment;
        System.out.println("Thank you for your payment of $" + payment);
        viewBalance();
    }

    // Show status
    public String showInfo() {
        return "\nName: " + firstName + " " + lastName +
                "\nGrade Level: " + gradeYear +
                "\nStudent ID: " + studentID +
                "\nCourses Enrolled: " + courses +
                "\nBalance: $" + tuitionBalance;
    }
}
