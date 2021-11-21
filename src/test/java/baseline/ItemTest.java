/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    // Create an item before each test
    Item test;
    @BeforeEach
    private void initItem() {
        test = new Item("Name","A-XXX-XXX-XXX","1.00");
    }

    // Test the name getter method
    @Test
    void getName() {
        assertEquals("Name",test.getName());
    }

    // Test the name setter method
    @Test
    void setName() {
        test.setName("New name");
        assertEquals("New name",test.getName());
    }

    // Test the serial number getter method
    @Test
    void getSerialNumber() {
        assertEquals("A-XXX-XXX-XXX",test.getSerialNumber());
    }

    // Test the serial number setter method
    @Test
    void setSerialNumber() {
        test.setSerialNumber("B-YYY-YYY-YYY");
        assertEquals("B-YYY-YYY-YYY",test.getSerialNumber());
    }

    // Test the cost getter method
    @Test
    void getCost() {
        assertEquals("$1.00",test.getCost());
    }

    // Test the cost setter method
    @Test
    void setCost() {
        test.setCost("2");
        assertEquals("$2.00",test.getCost());
    }

    // Test the string converter for cost method
    @Test
    void convertCost() {
        String newCost = test.convertCost("5");
        assertEquals("$5.00",newCost);
    }
}