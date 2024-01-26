package com.madeeasy.service.impl;

import com.madeeasy.dao.impl.UserDAOImpl;
import com.madeeasy.entity.Users;
import com.madeeasy.service.UserService;

import java.util.Scanner;

public class UserServiceImpl implements UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Users findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public void save() {
        System.out.print("Enter your name : ");
        String name = scanner.nextLine();
        System.out.print("Enter your email : ");
        String email = scanner.nextLine();
        System.out.print("Enter your password : ");
        String password = scanner.nextLine();
        Users user = Users.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
        this.userDAO.save(user);
    }

    @Override
    public void update() {

        System.out.print("Enter email to update : ");
        String emailToBeSearched = scanner.nextLine();
        Users userFound = this.userDAO.findByEmail(emailToBeSearched);
        if (userFound == null) {
            System.out.println("User not found");
            return;
        }
        System.out.print("Enter your name : ");
        String name = scanner.nextLine();
        System.out.print("Enter your email : ");
        String email = scanner.nextLine();
        System.out.print("Enter your password : ");
        String password = scanner.nextLine();

        userFound.setName(name);
        userFound.setEmail(email);
        userFound.setPassword(password);
        this.userDAO.update(userFound);
    }

    @Override
    public void getAllUsers() {
        this.userDAO.getAllUsers()
                .forEach(user -> {
                    System.out.println("id: " + user.getId());
                    System.out.println("name: " + user.getName());
                    System.out.println("email: " + user.getEmail());
                    System.out.println("password: " + user.getPassword());
                });
    }

    @Override
    public void deleteByEmail() {
        System.out.print("Enter email to delete : ");
        String email = scanner.nextLine();
        this.userDAO.deleteByEmail(email);
    }
}
