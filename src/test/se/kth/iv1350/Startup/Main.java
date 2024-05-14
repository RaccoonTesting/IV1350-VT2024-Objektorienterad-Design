package test.se.kth.iv1350.Startup;

import test.se.kth.iv1350.Controller.Controller;
import test.se.kth.iv1350.Integration.*;
import test.se.kth.iv1350.View.View;


public class Main {
    /**
     * Creates the objects needed for the sale
     * Sends accountingSystem, inventorySystem, Printer and Discount to the controller since it  will be communicating with them
     * Sends controller to the view (we will not be using the view in this seminar)
     * */
    public static void main(String[] args) {
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        Printer printer = new Printer();
        Controller controller = new Controller(accountingSystem, inventorySystem, printer);
        View view = new View(controller);

    }
}
