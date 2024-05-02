package Controller;

import Model.*;
import Integration.*;

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
    }

    public float endSale(){
        //Updating the inventorySystem and returning the Running total of this sale
        inventorySystem.updateInventory(sale.getItems());
        return sale.getRunningTotal();
    }

    /* 
    public float getDiscount(int customerId){
        return discount.getDiscount(customerId, sale);
    }*/

    public void getChange(){
        //getChange somehow? Should have return value
    }





}
