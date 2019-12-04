package com.example.uottawawalk_in;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestToggling {
    Availability Av=new Availability();
    @Test
    public void testToggle() {
        Av.toggleMon1();
        boolean actual = Av.getMon1(); // expected value is 212
        assertEquals("toggling fail", true , actual);
    }
}
