package test.se.kth.iv1350.Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ErrorLoggerTest {
    private FileReader fileReader;

    @BeforeAll
    void setup() throws FileNotFoundException{
        this.fileReader = new FileReader("errorLog.txt");
    }

    @Test
    void testLog() throws IOException {
        fileReader.read();
    }
}
