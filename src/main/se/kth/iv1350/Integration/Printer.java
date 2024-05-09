package main.se.kth.iv1350.Integration;
import main.se.kth.iv1350.Integration.ItemDTO;

public class Printer {
    private String reciept;

    public Printer(){

    }
    /**
     *  Print DTO for each item added
     */
    public void printItem(ItemDTO item, float total){
        System.out.println("Item ID: " + item.getItemID());
        System.out.println("Item name: " + item.getName());
        System.out.println("Item cost: " + item.getPrice() + " SEK");
        System.out.println("Vat: " + item.getVAT() + " %");
        System.out.println("Item description: " + item.getDescription());
        System.out.println("total: " + total + " SEK");
    }

    /**
     * Printing total cost
     */
    public void printEnd(float total){
        System.out.println("End sale");
        System.out.println("Total cost(including VAT): " + total + " SEK");
    }

    /**
     * Prints change that should be given to the customer
     */
    public void printChange(float change){
        System.out.println("Change to customer: " + change + " SEK");
    }

    /*public void printReciept()

     */
}
