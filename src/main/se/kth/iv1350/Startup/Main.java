package main.se.kth.iv1350.Startup;

import java.io.IOException;

import main.se.kth.iv1350.Controller.Controller;
import main.se.kth.iv1350.Integration.*;
import main.se.kth.iv1350.Util.ErrorLogger;
import main.se.kth.iv1350.Util.RevenueLogger;
import main.se.kth.iv1350.View.TotalRevenueView;
import main.se.kth.iv1350.View.View;


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
        ErrorLogger errorLogger = new ErrorLogger();
        Controller controller = new Controller(accountingSystem, inventorySystem, printer, errorLogger);
        try {
            RevenueLogger revenueLogger = new RevenueLogger();
            controller.addTotalRevenueObserver(revenueLogger);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("nähä");
            e.printStackTrace();
        }
        controller.addTotalRevenueObserver(new TotalRevenueView());
        View view = new View(controller);
        view.takeInput();
       
    }
}
