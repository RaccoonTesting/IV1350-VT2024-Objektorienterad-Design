package Startup;

import Controller.Controller;
import Integration.*;
import View.View;


public class Main {
    //Creates the objects needed for the sale
    //Sends accountingSystem, inventorySystem, Printer and Discount to the controller since it  will be communicating with them
    //Sends controller to the view (we will not be using the view in this seminar)
    public static void main(String[] args) {
        DiscountDatabase discount = new DiscountDatabase();
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        Printer printer = new Printer();
        Controller controller = new Controller(accountingSystem, inventorySystem, printer, discount);
        View view = new View(controller);
        controller.startSale();
        controller.addItem(1, "3");
        System.out.println(controller.endSale());
    }
}
