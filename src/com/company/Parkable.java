package com.company;

public interface Parkable {
    int PARKING_FEE = 500;
    public int getParkingFee();
    public void checkIn();
    public void checkOut();
    public void lostTicket();
}
