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

    public void addToSale(ItemDTO item, int quantity){
        for(int i = 0; i < quantity; i++)items.add(item);
        this.runningTotal += item.getPrice() * (1 + item.getVAT()) * quantity;
    }

    public float getRunningTotal(){
        return runningTotal;
    }

    public ArrayList<ItemDTO> getItems(){
        return this.items;
    }

    
}
