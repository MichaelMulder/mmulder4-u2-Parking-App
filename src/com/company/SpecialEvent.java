package com.company;

public class SpecialEvent implements FeeStrategy {
    private int fee;

    public SpecialEvent() {
        this.fee = 2000;
    }

    public SpecialEvent(int fee) {
        this.fee = fee;
    }

    @Override
    public void calcuator(Ticket ticket) {
        ticket.setTicketPrice(fee);
    }
}
