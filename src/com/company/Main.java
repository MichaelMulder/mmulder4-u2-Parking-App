package com.company;


public class Main {

    public static void main(String[] args) {
        FeeStrategyFactory feeStrategyFactory = new FeeStrategyFactoryImpl();

        ParkingGarage myGarage = new ParkingGarage(feeStrategyFactory);

        myGarage.run();
    }
}
