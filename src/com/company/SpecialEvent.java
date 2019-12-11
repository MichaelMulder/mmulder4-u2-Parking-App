package com.company;

public class SpecialEvent implements FeeStrategy {
    private int fee;

    public SpecialEvent() {
        this.fee = 2000;
    }

    public SpecialEvent(int fee) {
        this.fee = fee;
    }

    /**
     * assigns a ticket a flat rate for special events
     * @param ticket the ticket being assigned
     */
    @Override
    public void calcuator(Ticket ticket) {
        ticket.setTicketPrice(fee);
    }
}
