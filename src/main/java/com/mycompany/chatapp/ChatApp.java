/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;


public class ChatApp {
    
    
  

    private static int messageCount = 0;
    private static int totalAllowedMessages = 0;
    private static final List<Map<String, String>> messageList = new ArrayList<>();

    public static void main(String[] args) {
        
        // User Registration 
        String firstName =JOptionPane.showInputDialog("Enter your first name: ");
        String lastName = JOptionPane.showInputDialog("Enter your last name: ");
        
        //Username validation
        String username;
        while(true){
            username = JOptionPane.showInputDialog("Enter your username: ");
            if(Login.checkUserName(username)){
                JOptionPane.showMessageDialog(null,"Username successfully created.");
                break;
                 }else{
                JOptionPane.showMessageDialog(null,"Username must contain an underscore and be no more than 5 characters.");
                 }
           }
        //Password Validation
         String password;
         while(true){
             password = JOptionPane.showInputDialog("Enter your password: ");
              if
                      (Login.checkPasswordComplexity(password)){
                    JOptionPane.showMessageDialog(null,"Password successfully captured.");
                    break;
                   }else{
                       JOptionPane.showMessageDialog(null,"Password my have at least 8 characters,a UpperCase, a Digit and a specialCharacter.");
                   }
         }
         
         //Phone number Validation
         String phoneNumber;
         while(true){
             phoneNumber = JOptionPane.showInputDialog("Enter your phone number (start with +27):");
                 if
                  (Login.checkCellPhoneNumber(phoneNumber)){
                     JOptionPane.showMessageDialog(null,"Phone number successfully added.");
                     break;
                     
             }else{
              JOptionPane.showMessageDialog(null,"Phone number must start with +27 and be 12 characters.");
                     }
               }
         
         //Registration Complete
         JOptionPane.showMessageDialog(null,Login.registerUser(username, password, phoneNumber));
             
           //User Login
         
         String registeredUsername = username;
         String registeredPassword = password;              
                
        if (!login(registeredUsername,registeredPassword)) {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting...");
            System.exit(0);
        } //Exit if Login Failed
           
        // Main menu
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        while (true) {
            String option = JOptionPane.showInputDialog("""
                    Choose an option:
                    1) Send Messages
                    2) Show Recently Sent Messages
                    3) Quit""");

            switch (option) {
                case "1":
                    String countInput = JOptionPane.showInputDialog("How many messages do you want to send?");
                    try {
                        totalAllowedMessages = Integer.parseInt(countInput);
                        for (int i = 0; i < totalAllowedMessages; i++) {
                            handleMessage();
                        }
                        JOptionPane.showMessageDialog(null, "Total messages sent: " + messageCount);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid number.");
                    }
                    break;

                case "2":
                    showRecentMessages();
                    break;

                case "3":
                    saveMessagesToFile();
                    JOptionPane.showMessageDialog(null, "Messages saved. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }

    private static boolean login(String registeredUsername, String registeredPassword) {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        return registeredUsername.equals(username)&& registeredPassword.equals(password);
    }

    private static void handleMessage() {
        String recipient = JOptionPane.showInputDialog("Enter recipient (start with '+' and max 10 chars):");
        if (!validateRecipient(recipient)) {
            JOptionPane.showMessageDialog(null, "Invalid recipient. Must start with '+' and be 10 characters or fewer.");
            return;
        }

        String message = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
        if (!validateMessage(message)) {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
            return;
        }

        String[] options = {"Send Message", "Disregard Message", "Store Message to Send Later"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose an action for this message:",
                "Message Action",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 1) {
            JOptionPane.showMessageDialog(null, "Message disregarded.");
            return;
        }

        messageCount++;
        String messageId = generateMessageId();
        String messageHash = generateMessageHash(messageId, messageCount, message);

        Map<String, String> messageData = new HashMap<>();
        messageData.put("Message ID", messageId);
        messageData.put("Message Hash", messageHash);
        messageData.put("Recipient", recipient);
        messageData.put("Message", message);

        messageList.add(messageData);

        // Display message
        String output = "Message ID: " + messageId +
                        "\nMessage Hash: " + messageHash +
                        "\nRecipient: " + recipient +
                        "\nMessage: " + message;
        JOptionPane.showMessageDialog(null, output, "Message Sent", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String generateMessageId() {
        Random rand = new Random();
        int firstDigit = rand.nextInt(9) + 1;
        long remaining = (long) (Math.random() * 1_000_000_000L);
        return String.format("%d%09d", firstDigit, remaining);
    }

    private static boolean validateRecipient(String recipient) {
        return recipient != null && recipient.startsWith("+") && recipient.length() <= 10;
    }

    private static boolean validateMessage(String message) {
        return message != null && message.length() <= 250;
    }

    private static String generateMessageHash(String id, int count, String msg) {
        String[] words = msg.trim().split("\\s+");
        String first = words.length > 0 ? words[0].toUpperCase() : "";
        String last = words.length > 1 ? words[words.length - 1].toUpperCase() : first;
        return id.substring(0, 2) + ":" + count + ":" + first + last;
    }

    private static void showRecentMessages() {
        if (messageList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages sent yet.");
            return;
        }

        StringBuilder messages = new StringBuilder("Recent Messages:\n\n");
        for (Map<String, String> msg : messageList) {
            messages.append("To: ").append(msg.get("Recipient"))
                    .append("\nMessage: ").append(msg.get("Message"))
                    .append("\nHash: ").append(msg.get("Message Hash"))
                    .append("\n\n");
        }

        JOptionPane.showMessageDialog(null, messages.toString(), "Recent Messages", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void saveMessagesToFile() {
        try (FileWriter file = new FileWriter("messages.json")) {
            StringBuilder json = new StringBuilder("[\n");

            for (int i = 0; i < messageList.size(); i++) {
                Map<String, String> msg = messageList.get(i);
                json.append("  {\n")
                    .append("    \"Message ID\": \"").append(msg.get("Message ID")).append("\",\n")
                    .append("    \"Message Hash\": \"").append(msg.get("Message Hash")).append("\",\n")
                    .append("    \"Recipient\": \"").append(msg.get("Recipient")).append("\",\n")
                    .append("    \"Message\": \"").append(msg.get("Message")).append("\"\n")
                    .append("  }");
                if (i < messageList.size() - 1) {
                    json.append(",");
                }
                json.append("\n");
            }

            json.append("]");

            file.write(json.toString());
            file.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving messages to file.");
        }
    }

    
    }
