package main.se.kth.iv1350.Integration;

public class ItemIDNotFoundException extends Exception {
    /**
     * @param id is the item id that is passed if item can't be found in the inventory
     *  ItemIDNotFoundException give message when trown
     */
    public ItemIDNotFoundException(String id){
        super("No item found with ID: " + id);
    }
    
}
