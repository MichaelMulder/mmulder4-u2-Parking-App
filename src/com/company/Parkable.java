package com.company;

public interface Parkable {
    double PARKING_FEE = 500;
    double getParkingFee();
    void checkIn();
    void checkOut();
    void lostTicket();
}
