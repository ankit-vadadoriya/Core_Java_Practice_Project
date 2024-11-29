package com.example.File_Hidding_Project.views;

import com.example.File_Hidding_Project.dao.UserDAO;
import com.example.File_Hidding_Project.model.User;
import com.example.File_Hidding_Project.service.GenerateOTP;
import com.example.File_Hidding_Project.service.SendOTPService;
import com.example.File_Hidding_Project.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the application");
        System.out.println("Press 1 to Login");
        System.out.println("Press 2 to Signup");
        System.out.println("Press 3 to Exit");
        System.out.print("Enter your choice : ");
        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        switch (choice){
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your email : ");
        String email = sc.nextLine();
        try {
            if (UserDAO.isExists(email)){
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, genOTP);
                System.out.print("Enter the OTP : ");
                String otp = sc.nextLine();
                if (otp.equals(genOTP)){
                    new UserView(email).home();
                } else {
                    System.out.println("Wrong OTP");
                }
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name : ");
        String name = sc.nextLine();
        System.out.print("Enter your email : ");
        String email = sc.nextLine();

        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, genOTP);
        System.out.print("Enter the OTP : ");
        String otp = sc.nextLine();
        if (otp.equals(genOTP)){
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            switch (response){
                case 0 -> System.out.println("User already exists");
                case 1 -> System.out.println("User registered");
            }
        } else {
            System.out.println("Wrong OTP");
        }
    }
}
