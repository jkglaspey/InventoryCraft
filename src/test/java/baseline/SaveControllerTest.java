/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveControllerTest extends ImportController {

    // Create an object for testing
    SaveController test;
    @BeforeEach
    void initValues() {
        // create a list to copy / export
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item 1","A-aaa-aaa-aaa","1"));
        items.add(new Item("Item 2","B-bbb-bbb-bbb","2"));
        test = new SaveController(items);
    }

    // Delete potentially created files for re-testability
    @AfterEach
    void deleteFiles() {
        // TSV file
        if(!test.isFilePathInvalid("./data/test/testFile.txt")) new File("./data/test/testFile.txt").delete();

        // JSON file
        if(!test.isFilePathInvalid("./data/test/testFile.json")) new File("./data/test/testFile.json").delete();

        // HTML file
        if(!test.isFilePathInvalid("./data/test/testFile.html")) new File("./data/test/testFile.html").delete();
    }

    // Test that a specified file path is valid
    @Test
    void isFilePathInvalid() {
        // invalid path
        assertTrue(test.isFilePathInvalid("./poop"));

        // valid path
        assertFalse(test.isFilePathInvalid("./data/test/testTSV.txt"));
    }

    // Test that the data can be saved as a TSV formatted .txt file
    @Test
    void saveToTSV() {
        // write the data to a .txt
        test.saveToTSV("./data/test","testFile");

        // read data from TSV
        // note: I'm using ImportController to get the data, which is also being JUnit tested to verify it's
        // accurate
        ImportController readFiles = new ImportController();
        List<Item> values = readFiles.parseTSVFile(new File("./data/test/testFile.txt"));

        // confirm values were stored correctly
        assertEquals(2,values.size());
        assertEquals("Item 1",values.get(0).getName());
        assertEquals("Item 2",values.get(1).getName());
    }

    // Test that the data can be saved as a .json
    @Test
    void saveToJSON() {
        // write the data to a .json
        test.saveToJSON("./data/test", "testFile");

        // read data from TSV
        // note: I'm using ImportController to get the data, which is also being JUnit tested to verify it's
        // accurate
        List<Item> values = new ImportController().parseJSONFile(new File("./data/test/testFile.json"));

        // confirm values were stored correctly
        assertEquals(2, values.size());
        assertEquals("Item 1", values.get(0).getName());
        assertEquals("Item 2", values.get(1).getName());
    }

    // Test that the data can be saved as a .html
    @Test
    void saveToHTML() {
        // write the data to a .html
        test.saveToHTML("./data/test","testFile");

        // read data from html
        // note: I'm using ImportController to get the data, which is also being JUnit tested to verify it's
        // accurate
        ImportController readFiles = new ImportController();
        List<Item> values = readFiles.parseHTMLFile(new File("./data/test/testFile.html"));

        // confirm values were stored correctly
        assertEquals(2,values.size());
        assertEquals("Item 1",values.get(0).getName());
        assertEquals("Item 2",values.get(1).getName());
    }

    // Test that this method concatenates a path and a name to a valid file path
    @Test
    void combinePath() {
        // concatenate strings into valid file path
        assertEquals("./data/test/testFile.txt",test.combinePath("./data/test","testFile",".txt"));
    }
}