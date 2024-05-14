package main.se.kth.iv1350.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import main.se.kth.iv1350.Model.TotalRevenueObserver;

public class RevenueLogger implements TotalRevenueObserver {
    FileWriter fileWriter;
    LocalDateTime logTime;

    public RevenueLogger() throws IOException{
            this.fileWriter = new FileWriter("revenueLog.txt", true);
    }

    public void newPaymentRevenue(double payAmount) {
        this.logTime = LocalDateTime.now();
        try {
            fileWriter.write(logTime.toString() + "\n" + payAmount + " SEK\n\n");
            fileWriter.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("reeeee");
            e.printStackTrace();
        }
    }
    
}
