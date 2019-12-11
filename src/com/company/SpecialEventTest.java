package com.company;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpecialEventTest {

    @Test
    public void testCalculator() {
        Ticket testTicket = new Ticket();

        SpecialEvent specialEvent = new SpecialEvent();

        specialEvent.calcuator(testTicket);


        Assertions.assertEquals(2000, testTicket.getTicketPrice());
    }
}