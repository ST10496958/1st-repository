/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class MessageTest {

    @Before
    public void setup() {
        // Add any necessary setup/reset logic here
    }

    @Test
    public void testCheckMessageIDValid() {
        assertTrue(Message.checkMessageID("1234567890"));
    }

    @Test
    public void testCheckMessageIDInvalid() {
        assertFalse(Message.checkMessageID("12345678901")); // 11 characters
    }

    @Test
    public void testCheckRecipientCellValid() {
        assertTrue(Message.checkRecipientCell("+2781234567"));
    }

    @Test
    public void testCheckRecipientCellInvalidNoPlus() {
        assertFalse(Message.checkRecipientCell("2781234567"));
    }

    @Test
    public void testCheckRecipientCellInvalidShort() {
        assertFalse(Message.checkRecipientCell("+27123"));
    }

    @Test
    public void testValidateMessageLengthTooShort() {
        Message msg = new Message("+2781234567", "Short msg");
        assertFalse(msg.validateMessageLength());
    }

    @Test
    public void testValidateMessageLengthTooLong() {
        String longMsg = new String(new char[251]).replace("0", "a");
        Message msg = new Message("+2781234567", longMsg);
        assertFalse(msg.validateMessageLength());
    }

    @Test
    public void testValidateMessageLengthValid() {
        String validMsg = new String(new char[250]).replace("0", "a");
        Message msg = new Message("+2781234567", validMsg);
        assertTrue(msg.validateMessageLength());
    }

    @Test
    public void testSendMessageCountIncreases() {
        int initialCount = Message.returnTotalMessages();
        String validMsg = new String(new char[250]).replace("0", "a");
        Message msg = new Message("+2781234567", validMsg);
        msg.sendMessage();
        assertEquals(initialCount + 1, Message.returnTotalMessages());
    }

    @Test
    public void testSendMessageTooShortNotAdded() {
        int initialCount = Message.returnTotalMessages();
        Message msg = new Message("+2781234567", "short");
        msg.sendMessage();
        assertEquals(initialCount, Message.returnTotalMessages());
    }

    @Test
    public void testSendMessageViaConsoleChoice1Sent() {
        Scanner scanner = new Scanner("1\n");
        String result = Message.sendMessage(scanner, "+2781234567", new String(new char[250]).replace("0", "a"));
        assertEquals("Sent", result);
    }

    @Test
    public void testSendMessageViaConsoleChoice2Stored() {
        Scanner scanner = new Scanner("2\n");
        String result = Message.sendMessage(scanner, "+2781234567", new String(new char[250]).replace("0", "a"));
        assertEquals("Stored", result);
    }

    @Test
    public void testSendMessageViaConsoleChoice3Disregarded() {
        Scanner scanner = new Scanner("3\n");
        String result = Message.sendMessage(scanner, "+2781234567", new String(new char[250]).replace("0", "a"));
        assertEquals("Disregarded", result);
    }

    @Test
    public void testPrintMessagesNotEmptyAfterSend() {
        String validMsg = new String(new char[250]).replace("0", "a");
        Message msg = new Message("+2781234567", validMsg);
        msg.sendMessage();
        String output = Message.printMessages();
        assertTrue(output.contains("Recipient: +2781234567"));
    }
}