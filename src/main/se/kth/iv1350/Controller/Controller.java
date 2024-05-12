package main.se.kth.iv1350.Controller;

import main.se.kth.iv1350.Model.*;
import main.se.kth.iv1350.Integration.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private ExternalAccountingSystem accountingSystem;
    private ExternalInventorySystem inventorySystem;
    private Printer printer;
    private Sale sale;
    private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<TotalRevenueObserver>();
    /**
     * Instances for controller
     */
    public Controller(ExternalAccountingSystem accountingSystem, ExternalInventorySystem inventorySystem, Printer printer) {
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
        this.printer = printer;
    }

    /**
     *  New sale is started
     */
    public void startSale() {
        this.sale = new Sale();
        sale.addAlltotalRevenueObservers(totalRevenueObservers);
    }

    /**
     * ItemID is scanned and quantity is added by the cashier.
     * Get the item from the inventory system using ItemID
     * Add item to this sale with the quantity
     * @throws ItemIDNotFoundException 
     * */
    public ItemDTO addItem(int quantity, String itemID) throws ItemIDNotFoundException, DataBaseNotFoundException{
        
        if(itemID == "dead_server"){
            throw new DataBaseNotFoundException();
        }
        
        ItemDTO item = inventorySystem.getItem(itemID);
        this.sale.addToSale(item, quantity);
        return item;
    }

    /**
     * Updating inventory system
     * Sends information to Accounting system
     * Sending info to Printer
     * Asks for payment
     */
    public void sendToExternalSystems(){
        inventorySystem.updateInventory(sale.getQuantities());
        accountingSystem.sendToAccounting(sale.getQuantities());
        //printer.printReciept(sale.getItems(), sale.getTime(), sale.getRunningTotal());
        sale.notifyObservers();
    }

    public void pay(float cash){
        sale.getChange(cash);
        printer.printReciept(sale);
        }


    public float getRunningTotal(){
        return sale.getRunningTotal();
    }
    public void addTotalRevenueObserver(TotalRevenueObserver observerToGetAdded) {
        totalRevenueObservers.add(observerToGetAdded);
    }



    /* 
    public float getDiscount(int customerId){
        return discount.getDiscount(customerId, sale);
    }*/

    /**
     * Calculating change
     */
    





}
