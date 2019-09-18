package com.company;

import java.time.Duration;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner keyboard = new Scanner(System.in);
        HashMap<Vehicle, TicketType> ticketList = new HashMap<>();
        int goIn = 0;
        try {
            ticketList = GarageReader.readTicketsFile("TicketsDB.ser");
            System.out.println(ticketList.size() + " Tickets Loaded");
        } catch (Exception e) {
           e.printStackTrace();
            try {
                GarageWriter.writeTicketFile("TicketsDB.ser", ticketList);
                System.out.println("Created new TicketsDB");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.print(menuHeader());
        System.out.print(checkInMenu());
        while (goIn != 3) {
            String input = keyboard.nextLine();
            goIn = Integer.parseInt(input);
            // Display Menu
            TicketType ticketType;
            int i;
            switch (goIn) {
                case 1:
                    i = ticketList.size();
                    Vehicle car = new Vehicle();
                    car.setId(++i);
                    System.out.print(menuHeader());
                    System.out.print(checkOutMenu());
                    car.checkIn();
                    int goOut;
                    input = keyboard.nextLine();
                    goOut = Integer.parseInt(input);
                    switch (goOut) {
                        case 1:
                            car.checkOut();
                            ticketType = TicketType.CHECKED_OUT;
                            ticketList.put(car,ticketType);
                            System.out.print(menuHeader());
                            checkOutBill(car);
                            TimeUnit.SECONDS.sleep(2);
                            System.out.print(menuHeader());
                            System.out.print(checkInMenu());
                            break;
                        case 2:
                            car.lostTicket();
                            ticketType = TicketType.LOST_TICKET;
                            ticketList.put(car, ticketType);
                            System.out.print(menuHeader());
                            lostTicketBill(car);
                            TimeUnit.SECONDS.sleep(2);
                            System.out.print(menuHeader());
                            System.out.print(checkInMenu());
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
        return "\n Best Value Parking Garage\n" +
                "\n" +
                " =========================";
    }

    public static String checkInMenu() {
        return "\n 1 – Check/In\n" +
                "\n" +
                " 3 – Close Garage\n" +
                "\n" +
                " =>";
    }

    public static String checkOutMenu() {
        return "\n 1 – Check/Out\n" +
                "\n" +
                " 2 – Lost Ticket\n" +
                "\n" +
                " =>";
    }
    public static void checkOutBill(Vehicle car) {
        Duration hoursParked = Duration.between(car.getCheckInTime(), car.getCheckOutTime());
        System.out.printf("\n Receipt for a vehicle id %d \n \n \n \n %d hours parked %dam – %dpm\n \n $%d.00",
                car.getId(), hoursParked.toHoursPart(), car.getCheckInTime().getHour(), (car.getCheckOutTime().getHour() - 12), (car.getTicketPrice() / 100));
    }

    public static void lostTicketBill(Vehicle car) {
        System.out.printf("\n Receipt for a vehicle id %d \n \n \n \n Lost Ticket \n \n $%d.00", car.getId(), (car.getTicketPrice() / 100));
    }
}
