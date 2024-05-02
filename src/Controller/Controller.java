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
