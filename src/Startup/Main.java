package Startup;

import Controller.Controller;
import Integration.*;
import View.View;


public class Main {
    public static void main(String[] args) {
        DiscountDatabase discount = new DiscountDatabase();
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        Printer printer = new Printer();
        Controller controller = new Controller(accountingSystem, inventorySystem, printer, discount);
        View view = new View(controller);
        controller.startSale();
        controller.addItem(2, "3");
        controller.endSale();
    }
}
