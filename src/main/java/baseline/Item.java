/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to provide the framework of an Item object.

package baseline;

public class Item {

    // Declare variable for holding the name
    private String name;

    // Declare variable for holding the serial number
    private String serialNumber;

    // Declare variable for holding the cost
    private String cost;

    // Initialize the item object
    public Item(String name, String serialNumber, String cost) {
        // copy parameter items to the instance variables
        this.name = name;
        this.serialNumber = serialNumber;

        // convert cost to 2 decimal places
        this.cost = convertCost(cost);
    }

    // Get the item name
    public String getName() {
        return name;
    }

    // Set the item name
    public void setName(String name) {
        this.name = name;
    }

    // Get the item serial number
    public String getSerialNumber() {
        return serialNumber;
    }

    // Set the item serial number
    // Precondition: the string is a valid serial number (with "-")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    // Get the item cost
    public String getCost() {
        return cost;
    }

    // Set the item cost
    // Precondition: String can be cast to double
    public void setCost(String cost) {
        this.cost = cost;
    }

    // Convert a numerical string to monetary format, then convert back to string
    private String convertCost(String cost) {
        // convert string to number, round, and convert back to string
        String temp = String.valueOf(Math.round(100 * Float.parseFloat(cost)) / 100.00);

        // convert the string to be in USD format
        temp = '$' + temp;
        if(temp.charAt(temp.length() - 1) == '0') temp += '0';

        // return the string to the new item
        return temp;
    }
}
