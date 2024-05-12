package main.se.kth.iv1350.Integration;

public class DataBaseNotFoundException extends Exception {

    public DataBaseNotFoundException(){
        super("Data base cannot be reached");
    }
    
}
