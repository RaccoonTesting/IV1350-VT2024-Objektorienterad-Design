package Integration;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;

import Integration.ItemDTO;

public class ExternalInventorySystem {
    private ItemDTO itemDTO;
    private List<ItemDTO> inventoryItems = new ArrayList<>();

    public ExternalInventorySystem() {
        try {
            File inventory = new File("src/Integration/Inventory.txt");
            Scanner scanner = new Scanner(inventory);
            String[] info;
            while (scanner.hasNextLine()) {
                info = scanner.nextLine().split(",");

                itemDTO = new ItemDTO((String)info[0], info[1], Float.parseFloat(info[2]), Float.parseFloat(info[3]), info[4]);
                inventoryItems.add(itemDTO);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    public void updateInventory(List<ItemDTO> items) {
        //get items from External inventory system regarding provided list
        //count quantity of each item in provided list
        //push changes to External inventory system
    }

    public ItemDTO getItem(String itemID) {
        //Extract item information from external inventory system with provided ID
        //Return THIS item
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