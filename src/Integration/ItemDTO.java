package Integration;

public class ItemDTO {
    private int itemID;
    private float itemVAT;
    private String itemName;
    private float itemPrice;

    public ItemDTO(int itemID, String itemName, float itemPrice, float itemVAT ) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemVAT = itemVAT;
    }
    public int getItemID(){
        return this.itemID;
    }

    public String getName(){
        return this.itemName;
    }

    public float getPrice(){
        return this.itemPrice;
    }

    public float getVAT(){
        return this.itemVAT;
    }


    }
}
