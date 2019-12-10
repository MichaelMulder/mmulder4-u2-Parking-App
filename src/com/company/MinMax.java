package com.company;

import java.time.Duration;

public class MinMax implements FeeStrategy {
    private int initialFee;
    private int perHourFee;
    private int maximumFee;

    public MinMax() {
        this.initialFee = 500;
        this.perHourFee = 100;
        this.maximumFee = 1000;
    }

    public MinMax(int initialFee, int perHourFee, int maximumFee) {
        this.initialFee = initialFee;
        this.perHourFee = perHourFee;
        this.maximumFee = maximumFee;
    }

    @Override
    public void calcuator(Ticket ticket) {
        int totalPrice = initialFee;
        Duration timeCheckedIn = Duration.between(ticket.getCheckInTime(), ticket.getCheckOutTime());
        if(timeCheckedIn.toHours() < 10 && timeCheckedIn.toHours() > 3) {
            totalPrice += perHourFee * timeCheckedIn.toHours();
        } else {
            totalPrice += maximumFee;
        }
        ticket.setTicketPrice(totalPrice);
    }
}
