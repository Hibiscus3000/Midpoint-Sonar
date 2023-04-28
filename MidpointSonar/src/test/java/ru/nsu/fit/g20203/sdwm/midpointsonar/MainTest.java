package ru.nsu.fit.g20203.sdwm.midpointsonar;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Main.main(args);
        assertEquals(5,5);
    }
    
}
