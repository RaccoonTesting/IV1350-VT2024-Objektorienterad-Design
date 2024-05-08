package test.se.kth.iv1350.Controller;

import test.se.kth.iv1350.Model.*;
import test.se.kth.iv1350.Integration.*;
import java.util.Scanner;

public class Controller {

    private ExternalAccountingSystem accountingSystem;
    private ExternalInventorySystem inventorySystem;
    private Printer printer;
    private DiscountDatabase discount;
    private Sale sale;

    /*Instances for controller*/
    public Controller(ExternalAccountingSystem accountingSystem, ExternalInventorySystem inventorySystem, Printer printer,DiscountDatabase discount) {
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
        this.printer = printer;
        this.discount = discount;
    }
    /*New sale is started*/
    public void startSale() {
        this.sale = new Sale();
    }

    /*ItemID is scanned and quantity is added by the cashier.
     Get the item from the inventory system using ItemID
    Add item to this sale with the quantity */
    public void addItem(int quantity, String itemID){
        ItemDTO item = inventorySystem.getItem(itemID);
        this.sale.addToSale(item, quantity);
        printer.printItem(item, sale.getRunningTotal());
    }

    /*Updating inventory system
    Sends information to Accounting system
    Sending info to Printer
    Asks for payment
     */
    public void endSale(){
        inventorySystem.updateInventory(sale.getItems());
        accountingSystem.sendToAccounting(sale.getItems());
        printer.printEnd(sale.getRunningTotal());
        Scanner in = new Scanner(System.in);
        printer.printChange(getChange(in.nextFloat(), sale.getRunningTotal()));
        //printer.printReciept(sale.getItems(), sale.getTime(), sale.getRunningTotal());
    }

    /* 
    public float getDiscount(int customerId){
        return discount.getDiscount(customerId, sale);
    }*/

    /*Calculating change
     */
    private float getChange(float cash, float total){
        return cash - total;
    }





}
