package test.se.kth.iv1350.Integration;

public class DataBaseNotFoundException extends Exception {
    /**
     * Throws error message if database for Inventory is not found
     * To throw type dead_database when program is running
     */
    public DataBaseNotFoundException(){
        super("Data base cannot be reached");
    }

}
