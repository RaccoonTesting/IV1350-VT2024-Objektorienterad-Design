package test.se.kth.iv1350.Integration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;


public class ExternalInventorySystem {
    private HashMap<ItemDTO, Integer> inventory = new HashMap<>();

    /**
     *  Scans in text file with items that are in the "database"
     *  Parsing the all the items and information into the Item DTO creating an Array list with all items
     *  Creates a Hash map for the Inventory
     */
    public ExternalInventorySystem() {
        try {
            File inventoryFile = new File("src/main/se/kth/iv1350/Integration/Inventory.txt");
            Scanner scanner = new Scanner(inventoryFile);
            String[] info;
            ItemDTO itemDTO;
            while (scanner.hasNextLine()) {
                info = scanner.nextLine().split(",");

                itemDTO = new ItemDTO((String) info[0], info[1], Float.parseFloat(info[2]), Float.parseFloat(info[3]), info[4]);
                inventory.put(itemDTO, 10);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    /**
     * get items from External inventory system regarding provided list
     * count quantity of each item in provided list
     * push changes to External inventory system
     */
    public void updateInventory(HashMap<ItemDTO, Integer> quanteties) {
        for (ItemDTO item : quanteties.keySet()) {
            int quantity = inventory.get(item);
            inventory.put(item, quantity - quanteties.get(item));
        }

    }


    /**
     * Get item information from external inventory system with Item ID
     * Returns current item
     * @throws DataBaseNotFoundException
     */
    public ItemDTO getItem(String itemID) throws ItemIDNotFoundException, DataBaseNotFoundException{
        if(itemID.equals("dead_server")){
            throw new DataBaseNotFoundException();
        }
        for (ItemDTO i : this.inventory.keySet()) {
            if (i.getItemID().equals(itemID))return i;
        }

        throw new ItemIDNotFoundException(itemID);
        // TODO: handle exception

    }
}
