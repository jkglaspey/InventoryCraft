/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainSceneControllerTest {

    // Create new object for testing
    MainSceneController test;
    @BeforeEach
    void initValues() {
        test = new MainSceneController();
    }

    // test the getter method for inventory
    @Test
    void getInventory() {
        assertNotNull(test.getInventory());
    }

    // test the getter method for the list of items
    @Test
    void getListOfItems() {
        // the list is declared by FXML, so it is not null
        assertNotNull(test.getListOfItems());
    }

    // test that this method sets the list of items to the inventory
    @Test
    void resetListToInventory() {
        // add item to inventory
        test.getInventory().add(new Item("Item 1","A-aaa-aaa-aaa","1"));
        test.resetListToInventory();

        // verify the item copies over
        test.resetListToInventory();
        assertEquals("Item 1",test.getListOfItems().get(0).getName());
    }

    // test that this method returns the position of an item in the inventory by serial number
    @Test
    void getIndex() {
        // add items to inventory
        test.getInventory().add(new Item("Item 1","A-aaa-aaa-aaa","1"));
        test.getInventory().add(new Item("Item 2","A-aaa-aaa-aab","2"));

        // search for index
        assertEquals(1,test.getIndex(new Item("Item 2","A-aaa-aaa-aab","2")));
    }

    // test that this method successfully returns a list of items containing a substring of the searched name
    @Test
    void searchForName() {
        // add items to inventory
        test.getInventory().add(new Item("Item 1","A-aaa-aaa-aaa","1"));
        test.getInventory().add(new Item("Item 2","A-aaa-aaa-aab","2"));
        test.getInventory().add(new Item("Item 3","A-aaa-aaa-aac","3"));
        test.getInventory().add(new Item("Item 4","A-aaa-aaa-aad","4"));

        // search for "Item" and find all 4 values
        test.searchForName("Item");
        assertEquals(4,test.getListOfItems().size());

        // search for "Item 1" and find 1 value
        test.searchForName("Item 1");
        assertEquals(1,test.getListOfItems().size());

        // search for "Item 5" and find no values
        test.searchForName("Item 5");
        assertEquals(0,test.getListOfItems().size());
    }

    // test that this method successfully returns a list of items containing a substring of the searched serial number
    @Test
    void searchForSerialNumber() {
        // add items to inventory
        test.getInventory().add(new Item("Item 1","A-aaa-aaa-aaa","1"));
        test.getInventory().add(new Item("Item 2","A-aaa-aaa-aab","2"));
        test.getInventory().add(new Item("Item 3","A-aaa-aaa-aac","3"));
        test.getInventory().add(new Item("Item 4","A-aaa-aaa-aad","4"));

        // search for "A-aaa" and find all 4 values
        test.searchForSerialNumber("A-aaa");
        assertEquals(4,test.getListOfItems().size());

        // search for "b" and find 1 value
        test.searchForSerialNumber("b");
        assertEquals(1,test.getListOfItems().size());

        // search for "e" and find no values
        test.searchForSerialNumber("e");
        assertEquals(0,test.getListOfItems().size());
    }
}