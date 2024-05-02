package Model;
import Integration.ItemDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Sale {
    private float runningTotal;
    private ArrayList<ItemDTO> items;
    private LocalDateTime time;

    public Sale(){
        this.runningTotal = 0;
        this.time = LocalDateTime.now();
        this.items = new ArrayList<ItemDTO>();
    }
    // We get an Item DTO and the quantity from the controller
    // Adding the items multiple times to the sale depending on the quantity
    public void addToSale(ItemDTO item, int quantity){
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

    //Get local time for start of sale
    public LocalDateTime getTime(){
        return this.time;
    }

    
}
