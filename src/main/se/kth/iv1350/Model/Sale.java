package main.se.kth.iv1350.Model;
import main.se.kth.iv1350.Integration.ItemDTO;
import java.time.LocalDateTime;
import java.util.HashMap;


public class Sale {
    private float runningTotal;
    private HashMap<ItemDTO, Integer> quantities;
    private LocalDateTime time;
    private float paid;

    public Sale(){
        this.runningTotal = 0;
        this.time = LocalDateTime.now();
        this.quantities = new HashMap<ItemDTO, Integer>();
    }
    /**
     *  We get an Item DTO and the quantity from the controller
     *  Adding the items multiple times to the sale depending on the quantity
     */
    public void addToSale(ItemDTO item, int quantity){
        Integer currentQuantity = this.quantities.get(item);
        if(currentQuantity == null)this.quantities.put(item, quantity);
        else this.quantities.put(item, currentQuantity + quantity);
        this.runningTotal += item.getPrice() * (1 + (item.getVAT()/100)) * quantity;
    }

    /**
     * Returning running total of the sale
     */
    public float getRunningTotal(){
        return runningTotal;
    }


    public HashMap<ItemDTO, Integer> getQuantities() {
        return quantities;
    }

    public float getChange(float cash){
        this.paid = cash;
        return cash - this.runningTotal;
    }

    /**
     * Get local time for start of sale
     */
    public LocalDateTime getTime(){
        return this.time;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("---------------------------------\n");
        stringBuilder.append("Time of sale: " + this.getTime() + "\n");
        for(ItemDTO item : quantities.keySet()) 
            stringBuilder.append(quantities.get(item) + " st\n" + item.toString() + "\n\n");
        stringBuilder.append("Total cost(including VAT): " + getRunningTotal() + "\n");
        stringBuilder.append("Paid by customer: " + this.paid + " SEK\n");
        stringBuilder.append("Change to customer: " + getChange(paid) + " SEK\n");
        stringBuilder.append("---------------------------------\n");
        return stringBuilder.toString();
    }

    
}
