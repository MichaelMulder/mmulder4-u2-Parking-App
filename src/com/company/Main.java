package com.company;

import java.time.Duration;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        HashMap<TicketType, Vehicle> ticketList = new HashMap<>();
        int goIn = 0;
        try {
            ticketList = GarageReader.readTicketsFile("TicketsDB.ser");
            System.out.println(ticketList.size() + "Tickets Loaded");
        } catch (Exception e) {
            e.printStackTrace();
            GarageWriter.writeTicketFile("TicketsDB.ser", ticketList);
        }
        int i = ticketList.size();
        while (goIn != 3) {
            String input = keyboard.nextLine();
            goIn = Integer.parseInt(input);
            // Display Menu
            Vehicle car = new Vehicle();
            car.setId(i);
            TicketType ticketType;
            System.out.println(menuHeader());
            System.out.println(checkInMenu());
            switch (goIn) {
                case 1:
                    car.checkIn();
                    int goOut;
                    goOut = Integer.parseInt(input);
                    System.out.println(menuHeader());
                    System.out.println(checkOutMenu());
                    switch (goOut) {
                        case 1:
                            car.checkOut();
                            ticketType = TicketType.CHECKED_OUT;
                            checkOutBill(car);
                            ticketList.put(ticketType, car);
                            break;
                        case 2:
                            car.lostTicket();
                            ticketType = TicketType.LOST_TICKET;
                            lostTicketBill(car);
                            ticketList.put(ticketType, car);
                            break;
                    }
                    break;
                case 3:

                default:
                    System.exit(0);
            }
        }
    }
    public static String menuHeader() {
        return " Best Value Parking Garage\n" +
                "\n" +
                " =========================";
    }

    public static String checkInMenu() {
        return "1 – Check/In\n" +
                "\n" +
                " 3 – Close Garage\n" +
                "\n" +
                " =>_";
    }

    public static String checkOutMenu() {
        return "1 – Check/Out\n" +
                "\n" +
                " 2 – Lost Ticket\n" +
                "\n" +
                " =>_";
    }
    public static void checkOutBill(Vehicle car) {
        Duration hoursParked = Duration.between(car.getCheckInTime(), car.getCheckOutTime());
        System.out.printf("Receipt for a vehicle id %d \n" +
                "\n" +
                "\n" +
                "\n" +
                "%d hours parked %dam – %dpm\n \n $%.2d", car.getId(), hoursParked.toHoursPart(), car.getCheckInTime().getHour(), (car.getCheckOutTime().getHour() - 12), car.getTicketPrice());
    }

    public static void lostTicketBill(Vehicle car) {
        System.out.printf("Receipt for a vehicle id %d \n" +
                "\n" +
                "\n" +
                "\n" +
                "Lost Ticket\n \n $%.2d", car.getId(), car.getTicketPrice());
    }
}
