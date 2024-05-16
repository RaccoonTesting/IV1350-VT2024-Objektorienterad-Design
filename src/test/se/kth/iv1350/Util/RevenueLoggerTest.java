package test.se.kth.iv1350.Util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import main.se.kth.iv1350.Controller.Controller;
import main.se.kth.iv1350.Integration.ExternalAccountingSystem;
import main.se.kth.iv1350.Integration.ExternalInventorySystem;
import main.se.kth.iv1350.Integration.ItemDTO;
import main.se.kth.iv1350.Integration.Printer;
import main.se.kth.iv1350.Util.ErrorLogger;
import main.se.kth.iv1350.Util.RevenueLogger;
import main.se.kth.iv1350.View.TotalRevenueView;

@TestInstance(Lifecycle.PER_CLASS)
public class RevenueLoggerTest {
    private Scanner scanner;
    private Controller controller;
    private List<String> lines;

    @BeforeAll
    public void setUp() throws FileNotFoundException{
        File file = new File("revenueLog.txt");
        scanner = new Scanner(file);
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        Printer printer = new Printer();
        ErrorLogger errorLogger = new ErrorLogger();
        controller = new Controller(accountingSystem, inventorySystem, printer, errorLogger);
        try {
            RevenueLogger revenueLogger = new RevenueLogger();
            controller.addTotalRevenueObserver(revenueLogger);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("nähä");
            e.printStackTrace();
        }
        controller.addTotalRevenueObserver(new TotalRevenueView());        
    }

    @AfterAll
    public void tearDown(){
        scanner.close();
    }

    @Test
    void testNoPaymentRevenue() {
        controller.startSale();
        controller.pay(500);
        controller.sendToExternalSystems();
        lines = new ArrayList<String>();
        while(scanner.hasNext()){
            lines.add(scanner.nextLine());
        }
        assertEquals("0.0 SEK", lines.get(lines.size() - 1));
    }


    @Test
    void testNewPaymentRevenue() {
        controller.startSale();
        try {
            ItemDTO item = controller.addItem(2, "2");
            item = controller.addItem(2, "3");
        } catch (Exception e) {
        }
        controller.pay(500);
        controller.sendToExternalSystems();
        lines = new ArrayList<String>();
        while(scanner.hasNext()){
            lines.add(scanner.nextLine());
        }
        assertEquals("186.55999755859375 SEK", lines.get(lines.size() - 1));
    }
}
