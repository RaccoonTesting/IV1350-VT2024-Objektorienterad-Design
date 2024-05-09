package main.se.kth.iv1350.Integration;

public class ItemDTO {
    private String itemID;
    private float itemVAT;
    private String itemName;
    private float itemPrice;
    private String description;

    public ItemDTO(String itemID, String itemName, float itemPrice, float itemVAT, String description ) {
        /**
         *  Constructor for Items
         */
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemVAT = itemVAT;
        this.description = description;
    }

    /**
     * Getters for Item information
     */
    public String getItemID(){
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

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Item ID: " + this.getItemID() + "\n");
        stringBuilder.append("Item name: " + this.getName() + "\n");
        stringBuilder.append("Item cost: " + this.getPrice() + " SEK\n");
        stringBuilder.append("Vat: " + this.getVAT() + " %\n");
        stringBuilder.append("Item description: " + this.getDescription() + "\n");
        return stringBuilder.toString();
    }


    }

