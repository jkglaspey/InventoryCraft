/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemControllerTest {

    // Declare a test name, serial number, and cost
    ItemController test;
    String name;
    String serialNumber;
    String cost;
    @BeforeEach
    void initValues() {
        test = new ItemController();
        name = "Name";
        serialNumber = "A-XXX-XXX-XXX";
        cost = "1.00";
    }

    // test that this class successfully verifies each string by calling each verification method
    @Test
    void isNoErrors() {
        // true
        assertTrue(test.isNoErrors(name,serialNumber,cost));

        // test that it catches if one of the values are invalid
        assertFalse(test.isNoErrors("A",serialNumber,cost));
        assertFalse(test.isNoErrors(name,"A",cost));
        assertFalse(test.isNoErrors(name,serialNumber,"A"));
    }

    // test that this class recognizes blank strings
    @Test
    void isNotEmpty() {
        // not empty
        assertTrue(test.isNotEmpty(name,serialNumber,cost));

        // empty
        assertFalse(test.isNotEmpty("",serialNumber,cost));
        assertFalse(test.isNotEmpty(name,"",cost));
        assertFalse(test.isNotEmpty(name,serialNumber,""));
    }

    // test that this class recognizes invalid names
    @Test
    void isNameValid() {
        // valid name
        assertTrue(test.isNameValid("Name"));

        // invalid
        assertFalse(test.isNameValid("a"));
        assertFalse(test.isNameValid("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    // test that this class recognizes invalid serial numbers
    @Test
    void isSerialNumberValid() {
        // valid serial number
        assertTrue(test.isSerialNumberValid("A-111-222-333"));

        // invalid
        assertFalse(test.isSerialNumberValid("1-aaa-aaa-aaa"));
        assertFalse(test.isSerialNumberValid("a-=aa-aaa-aaa"));
        assertFalse(test.isSerialNumberValid("a-aaa-=aa-aaa"));
        assertFalse(test.isSerialNumberValid("a-aaa-aaa-=aa"));
    }

    // test that this class recognizes invalid costs
    @Test
    void isCostValid() {
        // valid cost
        assertTrue(test.isCostValid("1"));

        // invalid
        assertFalse(test.isCostValid("a"));
    }

    // test that this class recognizes duplicate serial numbers
    @Test
    void isSerialNumberUnique() {
        // create list with test values
        List<Item> list = new ArrayList<>();
        list.add(new Item("Item 1","A-aaa-aaa-aaa","1"));
        list.add(new Item("Item 2","B-bbb-bbb-bbb","2"));
        list.add(new Item("Item 3","C-ccc-ccc-ccc","3"));

        // adding new item, but serial number is unique
        assertTrue(test.isSerialNumberUnique("D-ddd-ddd-ddd",list.size(),list));

        // adding new item, but serial number is NOT unique
        assertFalse(test.isSerialNumberUnique("C-ccc-ccc-ccc",list.size(),list));

        // editing item, but not changing the serial number
        assertTrue(test.isSerialNumberUnique("B-bbb-bbb-bbb",1,list));
    }
}