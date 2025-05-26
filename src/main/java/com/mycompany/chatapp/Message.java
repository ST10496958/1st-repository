/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author lab_services_student
 */

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
//Import the JOptionPane to display messages
import javax.swing.JOptionPane;
//Import Scanner
import java.util.Scanner;
//import JSON to store
import org.json.*;


public class Message {

    private static int messageCount = 0;
    private static List<Message> sentMessages = new ArrayList<>();

    private String messageID;
    private String recipient;
    private String messageContent;
    private String messageHash;

    // Constructor
    public Message(String recipient, String messageContent) {
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageID = generateMessageID();
        this.messageHash = generateMessageHash();
    }

    // Constructor used for stored messages
    public Message(String messageID, String recipient, String messageContent, String messageHash) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = messageHash;
    }

    // Method 1: Check if Message ID is valid (no more than 10 characters)
    public static boolean checkMessageID(String id) {
        return id.length() <= 10;
    }

    // Method 2: Check if recipient cell is valid
    public static boolean checkRecipientCell(String cellNumber) {
        return cellNumber.startsWith("+") && cellNumber.length() >= 10;
    }

    // Method 3: Generate Message Hash
    private String generateMessageHash() {
        String[] words = messageContent.split(" ");
        String firstWord = words.length > 0 ? words[0].toUpperCase() : "";
        String lastWord = words.length > 1 ? words[words.length - 1].toUpperCase() : firstWord;
        return messageID.substring(0, 2) + ":" + messageCount + ":" + firstWord + lastWord;
    }

    // Method 4: Generate Message ID
    private String generateMessageID() {
        return String.format("%010d", (int) (Math.random() * 1_000_000_000));
    }

    // Method 5: Validate Message Length
    public boolean validateMessageLength() {
        if (messageContent.length() > 250) {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
            return false;
        } else if (messageContent.length() < 250) {
            JOptionPane.showMessageDialog(null, "Please enter a message of at least 50 characters.");
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Message sent.");
            return true;
        }
    }

    // Method 6: Send Message
    public void sendMessage() {
        if (validateMessageLength()) {
            sentMessages.add(this);
            messageCount++;
        }
    }

    // Method 7: Display Message Details
    public void displayMessageDetails() {
        String details = "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + messageContent;
        JOptionPane.showMessageDialog(null, details);
    }

    // Method 8: Display Total Messages Sent
    public static void displayTotalMessages() {
        JOptionPane.showMessageDialog(null, "Total messages sent: " + sentMessages.size());
    

    }

    // Method 9: Console-based message operation
    public static String sendMessage(Scanner scanner, String recipient, String content) {
        System.out.println("Do you want to (1) Send, (2) Store, or (3) Disregard the message?");
        String choice = scanner.nextLine();

        Message msg = new Message(recipient, content);

        switch (choice) {
            case "1":
                msg.sendMessage();
                return "Sent";

            case "2":
                
                return "Stored";

            case "3":
                return "Disregarded";

            default:
                return "Invalid";
        }
    }

    // Method 10: Print all messages
    public static String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages sent yet.";
        }
        StringBuilder result = new StringBuilder();
        for (Message m : sentMessages) {
            result.append("ID: ").append(m.messageID)
                  .append(", Recipient: ").append(m.recipient)
                  .append(", Content: ").append(m.messageContent)
                  .append(", Hash: ").append(m.messageHash)
                  .append("\n");    
        }
        return result.toString();
    }

    // Method 11: Get total messages sent
    public static int returnTotalMessages() {
        return sentMessages.size();
    }
}
