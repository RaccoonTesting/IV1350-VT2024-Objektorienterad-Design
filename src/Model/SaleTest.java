package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Integration.ItemDTO;

public class SaleTest {
    private Sale sale = null;

   @BeforeEach
   void setup(){
    sale = new Sale();
   }

   @AfterEach
   void teardown(){
    sale = null;
   }

    @Test
    void testEmptyGetItems() {
        ArrayList<ItemDTO> list = sale.getItems();
        assertTrue(list.isEmpty());
    }

    @Test
    void testGetRunningTotal() {
        float exTotal = 0;
        assertEquals(exTotal, sale.getRunningTotal(), 0.0);

    }

    @Test
    void testAddToSaleOneItemOneQuantity() {
        ItemDTO item = new ItemDTO("test", "tesitem", 5, 25, "test item desc");
        sale.addToSale(item, 1);
        float exTotal = (float)(5.0 * 1.25);
        assertEquals(exTotal, sale.getRunningTotal(), 0.0001);
        ArrayList<ItemDTO> exItemDTOs = new ArrayList<>();
        exItemDTOs.add(item);
        assertEquals(exItemDTOs, sale.getItems());
    }

    @Test
    void testAddToSaleOneItemMoreQuantity() {
        ItemDTO item = new ItemDTO("test", "tesitem", 5, 25, "test item desc");
        sale.addToSale(item, 3);
        float exTotal = (float)(5.0 * 1.25 * 3);
        assertEquals(exTotal, sale.getRunningTotal(), 0.0001);
        ArrayList<ItemDTO> exItemDTOs = new ArrayList<>();
        for(int i = 0; i < 3; i++)exItemDTOs.add(item);
        assertEquals(exItemDTOs, sale.getItems());
    }

    @Test
    void testAddToSaleMultpleItem() {
        ItemDTO item1 = new ItemDTO("test", "tesitem", 5, 25, "test item desc");
        ItemDTO item2 = new ItemDTO("test2", "tesitem", 10, 25, "test item desc");
        ItemDTO item3 = new ItemDTO("test3", "tesitem", 20, 25, "test item desc");
        sale.addToSale(item1, 1);
        sale.addToSale(item2, 1);
        sale.addToSale(item3, 1);
        float exTotal = (float)(5.0 * 1.25  + 10.0 * 1.25 + 20.0 * 1.25);
        assertEquals(exTotal, sale.getRunningTotal(), 0.0001);
        ArrayList<ItemDTO> exItemDTOs = new ArrayList<>();
        exItemDTOs.add(item1);
        exItemDTOs.add(item2);
        exItemDTOs.add(item3);
        assertEquals(exItemDTOs, sale.getItems());
    }
}
