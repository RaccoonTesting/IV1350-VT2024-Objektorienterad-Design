package test.se.kth.iv1350.Startup;

import test.se.kth.iv1350.Integration.*;
import test.se.kth.iv1350.View.View;
import test.se.kth.iv1350.Controller.Controller;


public class Main {
    //Creates the objects needed for the sale
    //Sends accountingSystem, inventorySystem, Printer and Discount to the controller since it  will be communicating with them
    //Sends controller to the view (we will not be using the view in this seminar)
    public static void main(String[] args) {
        DiscountDatabase discount = new DiscountDatabase();
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        Printer printer = new Printer();
        test.se.kth.iv1350.Controller.Controller controller = new Controller(accountingSystem, inventorySystem, printer, discount);
        View view = new View(controller);
        controller.startSale();
        controller.addItem(2, "3");
        controller.addItem(4, "5");

        controller.endSale();
    }
}
