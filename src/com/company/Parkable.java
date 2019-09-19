package com.company;

public interface Parkable {
    double PARKING_FEE = 500;
    public double getParkingFee();
    public void checkIn();
    public void checkOut();
    public void lostTicket();
}
