package Model;
import Integration.ItemDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Sale {
    //Private variables for this sale
    private float runningTotal;
    private ArrayList<ItemDTO> items;
    private LocalDateTime time;

    public Sale(){
        // At the start of any sale running total is 0
        // We save the current time create
        // An array list is created for the Items
        this.runningTotal = 0;
        this.time = LocalDateTime.now();
        this.items = new ArrayList<ItemDTO>();
    }

    public void addToSale(ItemDTO item, int quantity){
        // We get an Item DTO and the quantity from the controller
        // Adding an the items multiple times to the sale depending on the quantity
        for(int i = 0; i < quantity; i++)items.add(item);
        this.runningTotal += item.getPrice() * (1 + (item.getVAT()/100)) * quantity;
    }

    //Returning running total of the sale
    public float getRunningTotal(){
        return runningTotal;
    }

    //Returns Array list with all current items
    public ArrayList<ItemDTO> getItems(){
        return this.items;
    }

    public LocalDateTime getTime(){
        return this.time;
    }

    
}
