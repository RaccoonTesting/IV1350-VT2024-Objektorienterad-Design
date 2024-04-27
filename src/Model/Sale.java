package Model;
import Integration.ItemDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sale {
    private float runningTotal;
    private ArrayList<ItemDTO> items;
    LocalDateTime time = LocalDateTime.now();

    public Sale(){
        this.runningTotal = 0;
        this.time = time; //Fucked?
        this.items = new ArrayList<ItemDTO>();
    }

    public void addToSale(){}

    public float getRunningTotal(){
        return runningTotal;
    }
}
