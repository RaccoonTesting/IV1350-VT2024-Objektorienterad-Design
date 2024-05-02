package Controller;

import Model.*;
import Integration.*;
import java.util.Scanner;

public class Controller {

    private ExternalAccountingSystem accountingSystem;
    private ExternalInventorySystem inventorySystem;
    private Printer printer;
    private DiscountDatabase discount;
    private Sale sale;


    public Controller(ExternalAccountingSystem accountingSystem, ExternalInventorySystem inventorySystem, Printer printer,DiscountDatabase discount) {
        // Instances for this sale
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
        this.printer = printer;
        this.discount = discount;
    }

    public void startSale() {
        //New sale is started
        this.sale = new Sale();
    }

    public void addItem(int quantity, String itemID){
        // ItemID is scanned and quantity is added by the cashier.
        // Get the item from the inventory system using ItemID
        // Add item to this sale with the quantity
        ItemDTO item = inventorySystem.getItem(itemID);
        this.sale.addToSale(item, quantity);
        printer.printItem(item, sale.getRunningTotal());
    }

    public void endSale(){
        inventorySystem.updateInventory(sale.getItems());
        printer.printEnd(sale.getRunningTotal());
        Scanner in = new Scanner(System.in);
        printer.printChange(getChange(in.nextFloat(), sale.getRunningTotal()));
    }

    /* 
    public float getDiscount(int customerId){
        return discount.getDiscount(customerId, sale);
    }*/

    private float getChange(float cash, float total){
        //getChange somehow? Should have return value
        return cash - total;
    }





}
