package Quiz;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if (con == null) {
            System.out.println("Database connection failed");
            return;
        }

        System.out.println("Database connected successfully");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Roll Number: ");
        String rollNo = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        if (Login.authenticate(rollNo, password)) {
            System.out.println("Login successful");

            Quiz.startQuiz(rollNo);

            int score = ResultService.getScore(rollNo);
            System.out.println("Final Score: " + score);

        } else {
            System.out.println("Invalid credentials");
        }
    }
}

