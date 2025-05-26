/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class Login {
    
 static String storedUserName = "ho_pe";
    static String storedPassword = "Hope05@$";

    // 1. Check username format
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // 2. Check password complexity
    public static boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if ("@!#$%^&*()_+-{}:\"'<>,.?/".indexOf(c) >= 0) {
                hasSpecialChar = true;
            }
        }
        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }

    // 3. Check RSA cell phone number
    public static boolean checkCellPhoneNumber(String cellphonenumber) {
        return cellphonenumber.startsWith("+27") && cellphonenumber.length() == 12;
    }

    // 4. Register user
    public static String registerUser(String username, String password, String cellphonenumber) {
        if (!checkUserName(username)) {
            return "Incorrect username, please ensure it contains an underscore and is not more than 5 characters long.";
        } else if (!checkPasswordComplexity(password)) {
            return "Incorrect password, please ensure it has at least 8 characters, contains uppercase, special characters & digits.";
        } else if (!checkCellPhoneNumber(cellphonenumber)) {
            return "Invalid cellphone number. Must start with +27 and be 12 characters long.";
        } else {
            return "User registered successfully.";
        }
    }

    // 5. Login user
    public static boolean loginUser(String userName, String password) {
        return userName.equals(storedUserName) && password.equals(storedPassword);
    }

    // 6. Return login status
    public static String returnLoginStatus(String userName, String password) {
        if (loginUser(userName, null)) {
            return "successful login";
        } else {
            return "failed to login";
        }
    }

    // 7. Welcome message based on username format
    public static String validateUserNameAndWelcome(String userName, String firstName, String lastName) {
        if (checkUserName(userName)) {
            return "Welcome to QuikChat " + firstName + " " + lastName + ". It is great to see you.";
        } else {
            return "Username is incorrectly formatted.";
        }
    }
}
    

    