/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImportControllerTest {

    // create an object before each test
    ImportController test;
    List<Item> list;
    @BeforeEach
    void initValues() {
        test = new ImportController();
        list = new ArrayList<>();
    }

    // test that a valid file extension is returned
    @Test
    void getFileExtension() {
        // tsv
        assertEquals("txt",test.getFileExtension("test.txt"));

        // json
        assertEquals("json",test.getFileExtension("test.json"));

        // html
        assertEquals("html",test.getFileExtension("test.html"));

        // fail case
        assertEquals("",test.getFileExtension("test"));
    }

    // test that a file extension is valid
    @Test
    void isValidFileExtension() {
        // tsv
        assertTrue(test.isValidFileExtension("txt"));

        // json
        assertTrue(test.isValidFileExtension("json"));

        // html
        assertTrue(test.isValidFileExtension("html"));

        // fail case
        assertFalse(test.isValidFileExtension(""));
    }

    // test that a list of items can be imported
    @Test
    void importItems() {
        // we will test the actual results in next three test methods
        // this method ensures the list was created successfully and overrides the old list
        list.add(new Item("replace","R-epl-ace-000","0"));

        // test tsv
        list = test.importItems(test.openFile("./data/test/testTSV.txt"));
        assertNotEquals("replace",list.get(0).getName());
        assertEquals(1024,list.size());

        // test json
        list.set(0,new Item("replace","R-epl-ace-000","0"));
        list = test.importItems(test.openFile("./data/test/testJSON.json"));
        assertNotEquals("replace",list.get(0).getName());
        assertEquals(1024,list.size());

        // test html
        list.set(0,new Item("replace","R-epl-ace-000","0"));
        list = test.importItems(test.openFile("./data/test/testHTML.html"));
        assertNotEquals("replace",list.get(0).getName());
        assertEquals(1024,list.size());
    }

    // test that a .txt file can be parsed
    @Test
    void parseTSVFile() {
        // tsv
        list = test.parseTSVFile(test.openFile("./data/test/testTSV.txt"));
        // test first and last values to ensure list was copied
        assertEquals("Item 1",list.get(0).getName());
        assertEquals("Item 1024",list.get(list.size() - 1).getName());
    }

    // test that a .json file can be parsed
    @Test
    void parseJSONFile() {
        list = test.parseJSONFile(test.openFile("./data/test/testJSON.json"));
        // test first and last values to ensure list was copied
        assertEquals("Item 1",list.get(0).getName());
        assertEquals("Item 1024",list.get(list.size() - 1).getName());
    }

    // test that a .html file can be parsed
    @Test
    void parseHTMLFile() {
        list = test.parseHTMLFile(test.openFile("./data/test/testHTML.html"));
        // test first and last values to ensure list was copied
        assertEquals("Item 1",list.get(0).getName());
        assertEquals("Item 1024",list.get(list.size() - 1).getName());
    }

    // test that a json stream can be created
    @Test
    void createJsonStream() throws FileNotFoundException {
        // create stream
        File testFile = test.openFile("./data/test/testJSON.json");
        assertNotNull(test.createJsonStream(new FileInputStream(testFile)));

        // fail case
        File fail = test.openFile("Fail");
        try {
            test.createJsonStream(new FileInputStream(fail));
            fail("Exception not thrown");
        }
        // test passed
        catch(IOException e) {
            return;
        }
    }

    // test that a scanner stream can be created
    @Test
    void createScanner() {
        // create stream
        assertNotNull(test.createScanner(test.openFile("./data/test/testTSV.txt")));

        // fail case
        assertNull(test.createScanner(new File("Fail")));
    }
}