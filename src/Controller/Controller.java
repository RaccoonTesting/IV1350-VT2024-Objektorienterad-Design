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
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
        this.printer = printer;
        this.discount = discount;
    }

    public void startSale() {
        this.sale = new Sale();
    }

    public void addItem(int quantity, String itemID){
        ItemDTO item = inventorySystem.getItem(itemID);
        this.sale.addToSale(item, quantity);
    }

    public float endSale(){
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
