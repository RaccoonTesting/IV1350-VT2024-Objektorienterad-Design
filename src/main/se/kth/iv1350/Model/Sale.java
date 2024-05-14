package main.se.kth.iv1350.Model;
import main.se.kth.iv1350.Integration.ItemDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Sale {
    private float runningTotal;
    private HashMap<ItemDTO, Integer> quantities;
    private LocalDateTime time;
    private float paid;
    private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<TotalRevenueObserver>();

    /**
     * Sets instance for this sale on runningTotal, time
     * and quantities (a hashmap with the item DTO's connected to the quantity of the items)
     */
    public Sale(){
        this.runningTotal = 0;
        this.time = LocalDateTime.now();
        this.quantities = new HashMap<ItemDTO, Integer>();
    }
    /**
     *  We get an Item DTO and the quantity from the controller
     *  Adding both to the hash map, if item already exists adds quantity accordingly
     *  updates running total including VAT and quantity
     */
    public void addToSale(ItemDTO item, int quantity){
        Integer currentQuantity = this.quantities.get(item);
        if(currentQuantity == null)this.quantities.put(item, quantity);
        else this.quantities.put(item, currentQuantity + quantity);
        this.runningTotal += item.getPrice() * (1 + (item.getVAT()/100)) * quantity;
    }

    /**
     * @return runningTotal of the sale
     */
    public float getRunningTotal(){
        return runningTotal;
    }

    /**
     *
     * @return quantities of the specified Item DTO
     */
    public HashMap<ItemDTO, Integer> getQuantities() {
        return quantities;
    }

    /**
     *
     * @param cash is passed in to
     * @return (cash - this.runningTotal) witch is the change for the customer
     */
    public float getChange(float cash){
        this.paid = cash;
        return cash - this.runningTotal;
    }

    /**
     * @return this.time To get local time for start of sale
     */
    public LocalDateTime getTime(){
        return this.time;
    }

    /**
     *
     * @return stringBuilder.toString() to get all information from the hashmap ItemDTO + quantity in a pretty way
     */
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

    
    /**
     * Responsible for calling observer.
     */
    public void notifyObservers() {
        for (TotalRevenueObserver obs: totalRevenueObservers) {
            obs.newPaymentRevenue(runningTotal);
        }
    }

    /**
     * Adds every observer to the observer list in this class.
     * @param observersToGetAdded The specified observers to get added.
     */
    public void addAlltotalRevenueObservers(List<TotalRevenueObserver> observersToGetAdded) {
        totalRevenueObservers.addAll(observersToGetAdded);

    }
}
