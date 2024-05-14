package main.se.kth.iv1350.Integration;
import main.se.kth.iv1350.Model.Sale;

public class Printer {
    public Printer(){
    }
    /**
     * @param sale is passed and prints the output from sale.toString
     */
    public void printReciept(Sale sale){
        System.out.println(sale.toString());

    }
}
