package main.se.kth.iv1350.Controller;

import main.se.kth.iv1350.Model.*;
import main.se.kth.iv1350.Util.ErrorLogger;
import main.se.kth.iv1350.Integration.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private ExternalAccountingSystem accountingSystem;
    private ExternalInventorySystem inventorySystem;
    private Printer printer;
    private Sale sale;
    private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<TotalRevenueObserver>();
    private ErrorLogger errorLogger;

    /**
     * Instances for controller
     * @param accountingSystem sets instance of accounting system
     * @param inventorySystem sets instance of inventory system
     * @param printer sets instance of printer
     */
    public Controller(ExternalAccountingSystem accountingSystem, ExternalInventorySystem inventorySystem, Printer printer, ErrorLogger errorLogger) {
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
        this.printer = printer;
        this.errorLogger = errorLogger;
    }

    /**
     *  New sale is started
     *  Sets Observer to this sale
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
        try {
            ItemDTO item;
            item = inventorySystem.getItem(itemID);
            this.sale.addToSale(item, quantity);
            return item;
        } catch (ItemIDNotFoundException e) {
            // TODO Auto-generated catch block
            try {
                errorLogger.log(e);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            throw e;
        } catch (DataBaseNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                errorLogger.log(e);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            throw e;
        }
    }

    /**
     * Updating inventory system
     * Sends information to Accounting system
     * Sending info to Printer
     * Notify observer
     */
    public void sendToExternalSystems(){
        inventorySystem.updateInventory(sale.getQuantities());
        accountingSystem.sendToAccounting(sale.getQuantities());
    }

    /**
     * @param cash is input and a call to the getChange is made
     * Printer prints recipt with the sales information
     */
    public void pay(float cash){
        sale.getChange(cash);
        printer.printReciept(sale);
        }

    /**
     *
     * @return getRunningTotal from the current sale
     */
    public float getRunningTotal(){
        return sale.getRunningTotal();
    }
    public void addTotalRevenueObserver(TotalRevenueObserver observerToGetAdded) {
        totalRevenueObservers.add(observerToGetAdded);
    }



}
