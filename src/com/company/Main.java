package com.company;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner keyboard = new Scanner(System.in);
        HashMap<Vehicle, TicketType> ticketList = new HashMap<>();
        ticketList = GarageReader.readTicketsFile("TicketsDB.ser");
        int goIn = 0;
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
                    GarageWriter.writeTicketFile("TicketsDB.ser", ticketList);
                    break;
                case 3:
                    System.out.println(menuHeader());
                    closeGarage(ticketList);

                default:
                    System.exit(0);
            }
        }
    }

    /**
     * Creates the menu header for the menu
     * @return String
     */
    public static String menuHeader() {
        return "\n Best Value Parking Garage\n" +
                "\n" +
                " =========================";
    }

    /**
     * Creates check in menu
     * @return String
     */
    public static String checkInMenu() {
        return "\n 1 – Check/In\n" +
                "\n" +
                " 3 – Close Garage\n" +
                "\n" +
                " =>";
    }

    /**
     * Creates check out menu
     * @return String
     */
    public static String checkOutMenu() {
        return "\n 1 – Check/Out\n" +
                "\n" +
                " 2 – Lost Ticket\n" +
                "\n" +
                " =>";
    }

    /**
     * Prints out the the receipt for checking out and how many hours were parked
     * @param car the Vehicle being checked out
     */
    public static void checkOutBill(Vehicle car) {
        Duration hoursParked = Duration.between(car.getCheckInTime(), car.getCheckOutTime());
        System.out.printf("\n Receipt for a vehicle id %d " +
                        "\n " +
                        "\n " +
                        "\n " +
                        "\n %d hours parked %dam – %dpm" +
                        "\n " +
                        "\n $%.2f",
                car.getId(), hoursParked.toHoursPart(), car.getCheckInTime().getHour(), (car.getCheckOutTime().getHour() - 12), (car.getTicketPrice() / 100));
    }

    /**
     * Prints out receipt for a lost ticket
     * @param car the Vehicle that lost the ticket
     */
    public static void lostTicketBill(Vehicle car) {
        System.out.printf("\n Receipt for a vehicle id %d " +
                "\n " +
                "\n " +
                "\n " +
                "\n " +
                "Lost Ticket " +
                "\n " +
                "\n $%.2f", car.getId(), (car.getTicketPrice() / 100));
    }

    /**
     * Prints out the activity to date for the parking garage by
     * looking at a list of tickets given.
     * Calculates how many checkIns and Lost Tickets there were.
     * Calculates much was made in total and for each ticket type
     * how much was made from that alone.
     * @param ticketList the tickets being processed
     */
    public static void closeGarage(HashMap<Vehicle, TicketType> ticketList) {
        int checkInCount = 0;
        int lostTicketCount = 0;
        double sum = 0;
        double checkInSum = 0;
        double lostTicketSum = 0;

        for(Map.Entry<Vehicle, TicketType> entry: ticketList.entrySet()) {
           TicketType ticketType = entry.getValue();
           Vehicle car = entry.getKey();
           sum += car.getTicketPrice();
           if(ticketType.equals(TicketType.CHECKED_OUT)) {
               checkInCount++;
               checkInSum += car.getTicketPrice();
           } else {
               lostTicketCount++;
               lostTicketSum += car.getTicketPrice();
           }
        }

        System.out.printf(" Activity to Date\n" +
                "\n" +
                " \n" +
                "\n" +
                " $%.2f was collected from %d Check Ins\n" +
                "\n" +
                " $%.2f was collected from %d Lost Tickets\n" +
                "\n" +
                " \n" +
                "\n" +
                " $%.2f was collected overall", (checkInSum / 100), checkInCount, (lostTicketSum / 100), lostTicketCount, (sum / 100));

    }
}
