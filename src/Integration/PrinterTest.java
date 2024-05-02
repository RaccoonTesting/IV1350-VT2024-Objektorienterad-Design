package Integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testPrintChange() {
        float change = (float) 20.50;
        Printer printer = new Printer();
        printer.printChange(change);
        Assert.assertEquals("Change to customer: 20.5 SEK", outputStreamCaptor.toString().trim());
    }

    @Test
    void testPrintEnd() {
        float total = (float) 80.5;
        Printer printer = new Printer();
        printer.printEnd(total);
        Assert.assertEquals("End sale\nTotal cost(including VAT): 80.5 SEK", outputStreamCaptor.toString().trim());

    }

    @Test
    void testPrintItem() {
        ItemDTO item = new ItemDTO("test", "tesitem", 5, 25, "test item desc");
        float total = (float) 80.5;
        Printer printer = new Printer();
        printer.printItem(item, total);
        Assert.assertEquals("Item ID: test\n" + //
                        "Item name: tesitem\n" + //
                        "Item cost: 5.0 SEK\n" + //
                        "Vat: 25.0 %\n" + //
                        "Item description: test item desc\n" + //
                        "total: 80.5 SEK", outputStreamCaptor.toString().trim());
    }
}
