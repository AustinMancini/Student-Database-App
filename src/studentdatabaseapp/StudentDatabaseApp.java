package studentdatabaseapp;

import java.util.Scanner;
import java.sql.*;

public class StudentDatabaseApp {

    public static void main(String[] args) throws SQLException {
        // Ask how many new students we want to add
        System.out.print("Enter number of new students to enroll: ");
        Scanner in = new Scanner(System.in);
        int numOfStudents = in.nextInt();
        Student[] students = new Student[numOfStudents];

        // Create n number of new students
        for (int n = 0; n < numOfStudents; n++) {
            students[n] = new Student();
            students[n].enroll();
            students[n].viewBalance();
            students[n].payTuition();

            // Since we are returning something 'sout' to the console
            System.out.println(students[n].showInfo());
        }

        // ****POSTGRES DATABASE**** //
        for (int n = 0; n < numOfStudents; n++) {
            Connection conn = null;
            PreparedStatement stmt;
            try{
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_name","user","password");
                String sql="INSERT INTO college.students(student_id_number, first_name,last_name, grade_year) VALUES (?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, students[n].studentID);
                stmt.setString(2, students[n].firstName);
                stmt.setString(3, students[n].lastName);
                stmt.setInt(4, students[n].gradeYear);
                stmt.execute();
            } catch (SQLException se){
                System.out.println(se.getMessage());
            } finally {
                assert conn != null;
                conn.close();
            }
        }
    }
}
