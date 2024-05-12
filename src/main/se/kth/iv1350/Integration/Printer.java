package main.se.kth.iv1350.Integration;
import main.se.kth.iv1350.Model.Sale;

public class Printer {
    public Printer(){

    }
    public void printReciept(Sale sale){
        System.out.println(sale.toString());

    }
}
