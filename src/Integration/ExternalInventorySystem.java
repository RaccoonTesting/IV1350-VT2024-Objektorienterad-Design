package Integration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

                itemDTO = new ItemDTO(Integer.parseInt(info[0]), info[1], Float.parseFloat(info[2]), Float.parseFloat(info[3]), info[4]);
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

    public ItemDTO getItem(String ItemID) {
        //Extract item information from external inventory system with provided ID
        //Return THIS item
        return inventoryItems.get(ItemID);
    }

    
    public static void main(String args[]) {
        ExternalInventorySystem ex = new ExternalInventorySystem();
        System.out.println(ex.getItem(0).getName());
        System.out.println(ex.getItem(1).getName());
        System.out.println(ex.getItem(2).getName());
        System.out.println(ex.getItem(3).getName());
        System.out.println(ex.getItem(4).getName());

    }
}