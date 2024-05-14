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

    /**
     * @return a formated string of all the information that is contained in the Item DTO
     */
    @Override
    public String toString(){
        return "Item ID: " + this.getItemID() + "\nItem name: " + this.getName() + "\nItem cost: " + this.getPrice() + " SEK\nVat: " + this.getVAT() + " %\nItem description: " + this.getDescription();
    }


    }

