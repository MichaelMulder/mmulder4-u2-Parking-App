package com.company;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

public class Vehicle implements Parkable, Serializable {
    private int id = 1;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private double ticketPrice = 0;

    @Override
    public double getParkingFee() {
        return PARKING_FEE;
    }

    @Override
    public void checkIn() {
        this.ticketPrice += getParkingFee();
        calcCheckInTime();
    }

    @Override
    public void checkOut() {
        calcCheckOutTime();
        Duration timeCheckedIn = Duration.between(this.checkInTime, this.checkOutTime);
        this.ticketPrice += 100 * timeCheckedIn.toHoursPart();
    }

    @Override
    public void lostTicket() {
        this.ticketPrice += 2500;
    }

    private void calcCheckInTime() {
        int hourRange = ThreadLocalRandom.current().nextInt(7, 12 + 1);
        int mintRange = ThreadLocalRandom.current().nextInt(0, 59 + 1);
        int secsRange = ThreadLocalRandom.current().nextInt(0, 59 + 1);
        this.checkInTime = LocalTime.of(hourRange,mintRange,secsRange);
    }

    private void calcCheckOutTime() {
        int hourRange = ThreadLocalRandom.current().nextInt(13, 22 + 1);
        int mintRange = ThreadLocalRandom.current().nextInt(0, 59 + 1);
        int secsRange = ThreadLocalRandom.current().nextInt(0, 59 + 1);
        this.checkOutTime = LocalTime.of(hourRange,mintRange,secsRange);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }


    public double getTicketPrice() {
        return ticketPrice;
    }

}
