package com.company;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

public class Ticket implements Serializable {
    private double ticketPrice = 0;
    private int ticketNumber;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private TicketType ticketType;

    public Ticket() {
    }

    public Ticket(LocalTime checkInTime, LocalTime checkOutTime) {
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public void calcCheckInTime() {
        int hourRange = ThreadLocalRandom.current().nextInt(7, 12 + 1);
        int mintRange = ThreadLocalRandom.current().nextInt(0, 59 + 1);
        int secsRange = ThreadLocalRandom.current().nextInt(0, 59 + 1);
        this.checkInTime = LocalTime.of(hourRange,mintRange,secsRange);
    }

    public void calcCheckOutTime() {
        int hourRange = ThreadLocalRandom.current().nextInt(13, 22 + 1);
        int mintRange = ThreadLocalRandom.current().nextInt(0, 59 + 1);
        int secsRange = ThreadLocalRandom.current().nextInt(0, 59 + 1);
        this.checkOutTime = LocalTime.of(hourRange,mintRange,secsRange);
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }


}
