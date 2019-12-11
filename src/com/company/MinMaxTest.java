package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class MinMaxTest {
    LocalTime start = LocalTime.of(7, 30);
    LocalTime end = LocalTime.of(23, 0);
    @Test
    public void testCalculator() {
       Ticket testTicket = new Ticket(start, end);

       MinMax minMax = new MinMax();

       double expected = 1500;

       minMax.calcuator(testTicket);

       double actual = testTicket.getTicketPrice();

       Assertions.assertEquals(expected, actual, "Failed");
    }
}