/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import static org.junit.Assert.*;
import org.junit.Test;

public class LoginTest {

    @Test
    public void testCheckUserNameValid() {
        assertTrue(Login.checkUserName("ab_c"));
    }

    @Test
    public void testCheckUserNameInvalidNoUnderscore() {
        assertFalse(Login.checkUserName("abcde"));
    }

    @Test
    public void testCheckUserNameInvalidTooLong() {
        assertFalse(Login.checkUserName("abc_de"));
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue(Login.checkPasswordComplexity("Abcd123@"));
    }

    @Test
    public void testCheckPasswordComplexityMissingUppercase() {
        assertFalse(Login.checkPasswordComplexity("abcd123@"));
    }

    @Test
    public void testCheckPasswordComplexityMissingDigit() {
        assertFalse(Login.checkPasswordComplexity("Abcdefg@"));
    }

    @Test
    public void testCheckPasswordComplexityMissingSpecialChar() {
        assertFalse(Login.checkPasswordComplexity("Abcdefg1"));
    }

    @Test
    public void testCheckPasswordComplexityTooShort() {
        assertFalse(Login.checkPasswordComplexity("Ab1@"));
    }

    @Test
    public void testCheckCellPhoneNumberValid() {
        assertTrue(Login.checkCellPhoneNumber("+27123456789"));
    }

    @Test
    public void testCheckCellPhoneNumberInvalidPrefix() {
        assertFalse(Login.checkCellPhoneNumber("27123456789"));
    }

    @Test
    public void testCheckCellPhoneNumberInvalidLength() {
        assertFalse(Login.checkCellPhoneNumber("+27123"));
    }

    @Test
    public void testRegisterUserValid() {
        String result = Login.registerUser("ho_pe", "Hope05@$", "+27123456789");
        assertEquals("User registered successfully.", result);
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        String result = Login.registerUser("hope", "Hope05@$", "+27123456789");
        assertTrue(result.contains("Incorrect username"));
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        String result = Login.registerUser("ho_pe", "hope", "+27123456789");
        assertTrue(result.contains("Incorrect password"));
    }

    @Test
    public void testRegisterUserInvalidPhone() {
        String result = Login.registerUser("ho_pe", "Hope05@$", "0123456789");
        assertTrue(result.contains("Invalid cellphone number"));
    }

    @Test
    public void testLoginUserValidCredentials() {
        assertTrue(Login.loginUser("ho_pe", "Hope05@$"));
    }

    @Test
    public void testLoginUserInvalidCredentials() {
        assertFalse(Login.loginUser("ho_pe", "wrongPass"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        String status = Login.returnLoginStatus("ho_pe", "Hope05@$");
        assertEquals("successful login", status);
    }

    @Test
    public void testReturnLoginStatusFailure() {
        String status = Login.returnLoginStatus("ho_pe", "wrong");
        assertEquals("failed to login", status);
    }

    @Test
    public void testValidateUserNameAndWelcomeValid() {
        String welcome = Login.validateUserNameAndWelcome("ho_pe", "Hope", "Smith");
        assertTrue(welcome.contains("Welcome to QuikChat"));
    }

    @Test
    public void testValidateUserNameAndWelcomeInvalid() {
        String welcome = Login.validateUserNameAndWelcome("hope", "Hope", "Smith");
        assertEquals("Username is incorrectly formatted.", welcome);
    }
}