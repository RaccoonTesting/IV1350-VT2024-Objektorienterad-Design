package Integration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.HashMap;

//import Integration.ItemDTO;

public class ExternalInventorySystem {
    private ItemDTO itemDTO;
    private List<ItemDTO> inventoryItems = new ArrayList<>();
    private HashMap<ItemDTO, Integer> inventory = new HashMap<>();

    public ExternalInventorySystem() {
        // Scans in text file with items that are in the "database"
        // Parsing the all the items and information into the Item DTO creating an Array list with all items
        // Also creates a Hash map for the Inventory
        try {
            File inventoryFile = new File("src/Integration/Inventory.txt");
            Scanner scanner = new Scanner(inventoryFile);
            String[] info;
            while (scanner.hasNextLine()) {
                info = scanner.nextLine().split(",");

                itemDTO = new ItemDTO((String)info[0], info[1], Float.parseFloat(info[2]), Float.parseFloat(info[3]), info[4]);
                inventoryItems.add(itemDTO);
            }
            for(ItemDTO item : inventoryItems){
                inventory.put(item, 10);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    public void updateInventory(List<ItemDTO> items) {
        //get items from External inventory system regarding provided list
        //count quantity of each item in provided list
        //push changes to External inventory system
        for(ItemDTO item: items){
            int quantity = inventory.get(item);
            inventory.put(item, quantity - 1);
        }

    }

    public ItemDTO getItem(String itemID) {
        // Get item information from external inventory system with Item ID
        // Returns current item
            ItemDTO item = null;
           
            for(ItemDTO i : this.inventoryItems){ 
                if(i.getItemID().equals(itemID)){
                    item = i;
                }

            }
            return item;
    }
    
    public static void main(String args[]) {
        ExternalInventorySystem ex = new ExternalInventorySystem();
        ItemDTO item = ex.getItem("5");
        System.out.println(item);
        //System.out.println(ex.getItem("0").getName());

    }
}