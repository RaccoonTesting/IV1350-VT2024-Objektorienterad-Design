package main.se.kth.iv1350.Controller;

import main.se.kth.iv1350.Model.*;
import main.se.kth.iv1350.Integration.*;
public class Controller {

    private ExternalAccountingSystem accountingSystem;
    private ExternalInventorySystem inventorySystem;
    private Printer printer;
    private Sale sale;

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
    }

    public float pay(float cash){
        return sale.getChange(cash);
    }

    public float getRunningTotal(){
        return sale.getRunningTotal();
    }

    /* 
    public float getDiscount(int customerId){
        return discount.getDiscount(customerId, sale);
    }*/

    /**
     * Calculating change
     */
    





}
