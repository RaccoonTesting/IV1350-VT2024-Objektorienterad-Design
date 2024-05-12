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
    private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<TotalRevenueObserver>();

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
        return cash - this.runningTotal;
    }

    /**
     * Get local time for start of sale
     */
    public LocalDateTime getTime(){
        return this.time;
    }

    /**
     * Responsible for calling every observer.
     */
    public void notifyObservers() {
        for (TotalRevenueObserver obs: totalRevenueObservers) {
            obs.newPaymentRevenue(runningTotal);
        }
    }

    /**
     * Adds every observer to the observer list in this class.
     *
     * @param observersToGetAdded The specified observers to get added.
     */
    public void addAlltotalRevenueObservers(List<TotalRevenueObserver> observersToGetAdded) {
        totalRevenueObservers.addAll(observersToGetAdded);

    }
}
