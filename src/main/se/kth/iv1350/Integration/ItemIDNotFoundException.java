package main.se.kth.iv1350.Integration;

public class ItemIDNotFoundException extends Exception {

    public ItemIDNotFoundException(String id){
        super("No item found with ID: " + id);
    }
    
}
