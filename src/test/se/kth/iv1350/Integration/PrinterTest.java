package test.se.kth.iv1350.Integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.se.kth.iv1350.Integration.ItemDTO;
import main.se.kth.iv1350.Integration.Printer;
import main.se.kth.iv1350.Model.Sale;

public class PrinterTest {
    
private final PrintStream standardOut = System.out;
private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
private Printer printer = null;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testPrintReciept(){
        this.printer = new Printer();
        Sale sale = new Sale();
        LocalDateTime time = LocalDateTime.now().minusNanos(1);
        ItemDTO item1 = new ItemDTO("1", "testItem1", 20, 5, "test item 1");
        ItemDTO item2 = new ItemDTO("2", "testItem2", 25, 5, "test item 2");
        ItemDTO item3 = new ItemDTO("3", "testItem3", 30, 5, "test item 3");

        sale.addToSale(item1, 2);
        sale.addToSale(item2, 3);
        sale.addToSale(item3, 4);
        sale.getChange(500);
        printer.printReciept(sale);
        Assert.assertEquals("---------------------------------\n" +
        "Time of sale: " + time + "\n" +
        "3 st\n" +
        "Item ID: 2\n" +
        "Item name: testItem2\n" +
        "Item cost: 25.0 SEK\n" +
        "Vat: 5.0 %\n" +
        "Item description: test item 2\n" +
        "\n" +
        "4 st\n" +
        "Item ID: 3\n" +
        "Item name: testItem3\n" +
        "Item cost: 30.0 SEK\n" +
        "Vat: 5.0 %\n" +
        "Item description: test item 3\n" +
        "\n" +
        "2 st\n" +
        "Item ID: 1\n" +
        "Item name: testItem1\n" +
        "Item cost: 20.0 SEK\n" +
        "Vat: 5.0 %\n" +
        "Item description: test item 1\n" +
        "\n" +
        "Total cost(including VAT): 246.74998\n" +
        "Paid by customer: 500.0 SEK\n" +
        "Change to customer: 253.25002 SEK\n" +
        "---------------------------------", outputStreamCaptor.toString().trim());

    }
 
}
