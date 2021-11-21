# InventoryCraft
![Icon](https://i.imgur.com/t0idjOA.png)

### Welcome to InventoryCraft!
The Minecraft-inspired GUI which manages an inventory of items.


# Features:
 - Utilizes "Items," which can store a:
	 - Name
	 - Serial number
	 - Price
 - Can store up to 1024 items
 - Can modify previously existing items
 - Can sort each category by ascending / descending values
 - Can delete a specified item
 - Can delete all items
 - Can save the current inventory as a:
	 - Tab-separated .txt
	 - .json
	 - Table-formatted .html
- Can load a previously-saved inventory


**The following User Guide will help you understand how to use this program.**


## Items
Items are the objects which can be stored in this application. Make sure to adhere to the following criteria:
- Names must be between 2 to 256 characters in length (inclusive).
- Serial numbers must be in the format *A-XXX-XXX-XXX*, where *A* represents a letter, and *X* represents either a letter or digit.
- Serial numbers must be **unique!**
- Prices must be in *numerical* format. Values will be rounded to two decimal places.

![Item](https://i.imgur.com/4YGyRkp.png)
*Figure 1: Item view*


#  Loading Screen
Before being able to access the main features of this program, you will be shown an introduction scene.
- To start the application, select the **[Start Application]** button.
- To view the User Guide on this GitHub repository, select the **[Open User Guide]** button.
- If you opened the application by mistake, press the **[Exit Application]** button to leave the program.

![Load Scene](https://i.imgur.com/WB9ynbs.png)
*Figure 2: Loading Screen*

#  Main Screen
All of the features of the application can be viewed here.

![Main Scene](https://i.imgur.com/jIu15l3.png)
*Figure 3: Main screen*

## Create a new item

On startup, the inventory will not be populated. To add an item:

 1. Select the **[Create New Item]** Button.
 2. Enter the name in the **[Name]** text box.
 3. Enter the serial number in the **[Serial Number]** text box.
 4. Enter the cost in the **[Cost]** text box. 

 **Note:** Make sure each field is filled and in the correct format. Errors will appear if there is an invalid input.

 5. Select the **[Create New Item]** button to finish the action.

![Create a new item](https://i.imgur.com/9kbmLdK.png)
*Figure 4: Create a new item*

##  Remove item
There might be an item you want to remove from your inventory. To remove a specific item:

1. Select the desired item in the list.
2. Select the **[Delete Item]** button.

**Note:** This action will not prompt a confirmation screen, and cannot be undone. Make sure you wish to delete this item BEFORE pressing the button.

##  Remove all items
Suppose you have an inventory of items, but you wish to start over with a clean slate. To remove all items:

1. Select the **[Delete All Items]** button.
2. Confirm the action by selecting the **[Delete All Items]** button on the new scene.

**Note:** This action is irreversible. Make sure you wish to do this before pressing the confirmation button.

![Delete all items](https://i.imgur.com/7GQhZhL.png)
*Figure 5: Remove all items*

##  Edit selected item
Let's say that you wish to change the name of an item that already exists. To edit the data of a selected item:

1. Select the desired item in the list.
2. Select the **[Edit Item]** button.

**Note:** This option will not be available unless an item is selected.

Note that the item values are passed into the new scene.

3. Replace any of the fields with a new value. This includes:
	- The name
	- The serial number
	- The date

However, each of these values must be in the correct format. The serial number can match the the item you are modifying, but not any of the other existing items' numbers.

4. Select the **[Edit Item]** button to finish the action.

![Edit item](https://i.imgur.com/sUui0cK.png)
*Figure 6: Edit "Item" values*

##  Sort by category
It is possible to sort each category by value. For the "Name" and "Serial Number" columns, this would be alphabetically, whereas the "Cost" will be numerical ascending / descending. To sort the inventory by category:

1. Navigate to the *Column Header* of a specific group, This includes:
	- **Name**
	- **Serial Number**
	- **Cost**

2. Click the title *ONCE* to sort the list by **descending value.**
3. Click the title *TWICE* to sort the list by **ascending value.**
4. Click the title *THREE TIMES* to **reset** the sort of the list.

**Note:** Selecting a new title will give that sort priority to the previous one.

![Sort](https://i.imgur.com/8YtNwpb.png)
*Figure 7: Sort by ascending serial numbers*

##  Search for item
If the list gets too long, it can be hard to find a specific item. To search for a specific item:

1. Select the **[Name]** radio button to search by name.
2. Select the **[Serial #]** radio button to search by serial number.
3. Navigate to the **[Select Item]** text box, and enter the text you wish to search for.
4. Press **Enter** on your keyboard.

The list will now only display items with either names or serial numbers containing the text you entered.
**Note:** To undo a search, delete the text in the text field and press **Enter** on your keyboard.

![Search controls](https://i.imgur.com/00HfOKc.png)
*Figure 8: Search controls*

##  Save inventory to file
It's good practice to save your inventory in case you wish to access the items later. To save your current inventory of items to a file:

1. Select the **[Save Inventory]** button.
2. Select the **[TSV]** radio button to save the file as a tab-separated .txt file.
3. Select the **[HTML]** radio button to save the file as a table-formatted .html file.
4. Select the **[JSON]** radio button to save the file as a .json file.
5. Type the desired file name into the **[Name]** text box.

**Note:** It is valid to have a file without a name. Although... if you lose track of it, good luck ever finding it.

6. Select the **[Choose File Path:]** button to open a directory chooser popup window.
7. Navigate to the desired folder to save the new file and select **[Select Folder].**
8. Select the **[Save Items]** button to finish the action.

Whala! Now the list will be saved to the local storage. But, I wouldn't recommend viewing a JSON file, it can get a bit... ugly...

![Save inventory](https://i.imgur.com/G5YEc6Z.png)
*Figure 9: Save inventory to file*

##  Load inventory from file
Now that you have inventories saved to your computer, it is only appropriate to be able to view them in the application. To load in an inventory from a file:

1. Select the **[Load Inventory]** button.
2. Select the **[Choose File]** button to open a file chooser popup window.
3. Navigate to the desired file to be imported and select **[Open].**

This will enable the **[Load Items]** button.

4. Select the **[Load Items]** button to finish the action.

**Note:** This will override your current inventory. Make sure either your current items are saved or not important before attempting this.

![Load inventory](https://i.imgur.com/h9kwlxg.png)
*Figure 10: Load inventory from file*
