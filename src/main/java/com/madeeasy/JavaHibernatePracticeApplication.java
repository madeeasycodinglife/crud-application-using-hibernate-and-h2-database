package com.madeeasy;

import com.madeeasy.service.impl.UserServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class JavaHibernatePracticeApplication {
    private SessionFactory sessionFactory = null;
    private static UserServiceImpl userService = new UserServiceImpl();
    static Scanner scanner = new Scanner(System.in);

    public JavaHibernatePracticeApplication() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(JavaHibernatePracticeApplication.class, args);
        int choice = 0;
        do {
            System.out.println("Welcome to Hibernate CRUD application ");
            System.out.println("1. Save User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Get All Users");
            System.out.println("5. Exit");
            System.out.print("Enter your choice : ");

            // Handle input mismatch exception
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
                continue;
            }
            switch (choice) {
                case 1:
                    userService.save();
                    break;
                case 2:
                    userService.update();
                    break;
                case 3:
                    userService.deleteByEmail();
                    break;
                case 4:
                    userService.getAllUsers();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (true);
    }
}
