package main.se.kth.iv1350.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ErrorLogger {
    FileWriter fileWriter;
    LocalDateTime logTime;

    public ErrorLogger(){
        
        try {
            this.fileWriter = new FileWriter("errorLog.txt", true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("can't find file");
            e.printStackTrace();
        }

        
    }

    public void log(Exception error) throws IOException{
        this.logTime = LocalDateTime.now();
        fileWriter.write(logTime + "\n" + error.getMessage() + "\n\n");
        fileWriter.flush();
    }
}
