package Integration;

public class ItemDTO {
    private int itemID;
    private float itemVAT;
    private String itemName;
    private float itemPrice;
    private String description;

    public ItemDTO(int itemID, String itemName, float itemPrice, float itemVAT, String description ) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemVAT = itemVAT;
        this.description = description;
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

    public String getDescription(){
        return this.description;
    }


    }

