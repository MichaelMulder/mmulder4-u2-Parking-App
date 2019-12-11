package com.company;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParkingGarage {
    private FeeStrategyFactory feeStrategyFactory;

    public ParkingGarage(FeeStrategyFactory feeStrategyFactory) {
        this.feeStrategyFactory = feeStrategyFactory;
    }

    public void run() {
        Scanner keyboard = new Scanner(System.in);

        GarageReader garageInstance = GarageReader.getInstance();

        HashMap<Ticket, TicketType> ticketList = garageInstance.readTicketsFile("TicketsDB.ser");

        System.out.print(menuHeader());
        System.out.print(menu());
        int menuSelection = 0;

        while(menuSelection != 4) {
            String input = keyboard.nextLine();
            menuSelection = Integer.parseInt(input);
            int i;

            switch (menuSelection) {
                case 1:
                    System.out.println(menuHeader());
                    System.out.println(checkInMenu());
                    i = ticketList.size();
                    Ticket newTicket = new Ticket();
                    newTicket.setTicketNumber(++i);
                    newTicket.calcCheckInTime();
                    //check In
                    input = keyboard.nextLine();


                    int checkInSelection = Integer.parseInt(input);
                    switch (checkInSelection) {
                        case 1:
                            // Regular Ticket
                            newTicket.setTicketType(TicketType.PENDING);
                            System.out.print(menuHeader());
                            checkInTicket(newTicket);
                            ticketList.put(newTicket, newTicket.getTicketType());
                            break;
                        case 2:
                            // Special Event
                            newTicket.setTicketType(TicketType.SPECIAL_EVENT);
                            FeeStrategy feeStrategy = feeStrategyFactory.make(newTicket.getTicketType().toString());
                            feeStrategy.calcuator(newTicket);
                            System.out.print(menuHeader());
                            specialEventBill(newTicket);
                            ticketList.put(newTicket, newTicket.getTicketType());
                            break;
                    }

                    System.out.print(menuHeader());
                    System.out.print(menu());
                    break;
                case 2:
                    // check Out

                    System.out.print(menuHeader());
                    System.out.print(checkOutMenu());


                    input = keyboard.nextLine();


                    int checkOutSelection = Integer.parseInt(input);

                    switch (checkOutSelection) {
                        case 1:
                            // Regular Ticket

                            System.out.println(enterTicketNumber());
                            input = keyboard.nextLine();
                            int ticketId = Integer.parseInt(input);

                            Ticket ticketFound = findTicketById(ticketList, ticketId);

                            FeeStrategy feeStrategy = feeStrategyFactory.make(ticketFound.getTicketType().toString());
                            feeStrategy.calcuator(ticketFound);
                            System.out.print(menuHeader());
                            checkOutBill(ticketFound);
                            ticketList.put(ticketFound, ticketFound.getTicketType());
                            break;
                        case 2:
                            // Lost Ticket
                            Ticket lostTicket = findAPendingTicket(ticketList);
                            feeStrategy = feeStrategyFactory.make(lostTicket.getTicketType().toString());
                            feeStrategy.calcuator(lostTicket);
                            System.out.print(menuHeader());
                            lostTicketBill(lostTicket);
                            ticketList.put(lostTicket, lostTicket.getTicketType());
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + checkOutSelection);
                    }
                    System.out.print(menuHeader());
                    System.out.print(menu());
                    break;
                case 3:
                    // close Garage
                    GarageWriter.writeTicketFile("TicketsDB.ser", ticketList);
                    System.out.println(menuHeader());
                    closeGarage(ticketList);
                    break;
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
     * Creates main menu
     * @return String
     */
    public static String menu() {
        return "\n 1 – Check/In\n" +
                "\n" +
                " 2 – Check/Out\n" +
                "\n" +
                " 3 – Close Garage\n" +
                "\n" +
                " =>";
    }
    /**
     * Creates checkIn menu
     * @return String
     */
    public static String checkInMenu() {
        return "\n 1 – Check/In\n" +
                "\n" +
                " 2 – Special Event\n" +
                "\n" +
                " =>";
    }

    public static void checkInTicket(Ticket ticket) {
        System.out.printf("Ticket for a vehicle id %d\n" +
               "\n" +
               " \n" +
               "\n" +
               " %dam ", ticket.getTicketNumber(), ticket.getCheckInTime().getHour());
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

    public static String enterTicketNumber() {
        return "Ticket Number =>";
    }

    /**
     * Prints out the the receipt for checking out and how many hours were parked
     * @param ticket the Vehicle being checked out
     */
    public static void checkOutBill(Ticket ticket) {
        Duration hoursParked = Duration.between(ticket.getCheckInTime(), ticket.getCheckOutTime());
        System.out.printf("\n Receipt for a vehicle id %d " +
                        "\n " +
                        "\n " +
                        "\n " +
                        "\n %d hours parked %dam – %dpm" +
                        "\n " +
                        "\n $%.2f",
                ticket.getTicketNumber(), hoursParked.toHours(), ticket.getCheckInTime().getHour(), (ticket.getCheckOutTime().getHour() - 12), (ticket.getTicketPrice() / 100));
    }

    /**
     * Prints out receipt for a lost ticket
     * @param ticket the ticket lost
     */
    public static void lostTicketBill(Ticket ticket) {
        System.out.printf("\n Receipt for a vehicle id %d " +
                "\n " +
                "\n " +
                "\n " +
                "\n " +
                "Lost Ticket " +
                "\n " +
                "\n $%.2f", ticket.getTicketNumber(), (ticket.getTicketPrice() / 100));
    }

    /**
     * Prints out receipt for a special event ticket
     * @param ticket the special event ticket
     */
    public static void specialEventBill(Ticket ticket) {
        System.out.printf("\n Receipt for a vehicle id %d " +
                "\n " +
                "\n " +
                "\n " +
                "\n " +
                "Special Event" +
                "\n " +
                "\n $%.2f", ticket.getTicketNumber(), (ticket.getTicketPrice() / 100));
    }

    /**
     * Prints out the activity to date for the parking garage by
     * looking at a list of tickets given.
     * Calculates how many checkIns and Lost Tickets there were.
     * Calculates much was made in total and for each ticket type
     * how much was made from that alone.
     * @param ticketList the tickets being processed
     */
    public static void closeGarage(HashMap<Ticket, TicketType> ticketList) {
        int checkInCount = 0;
        int lostTicketCount = 0;
        int specialEventCount = 0;
        double sum = 0;
        double checkInSum = 0;
        double lostTicketSum = 0;
        double specialEventSum = 0;
        int pendingCount = 0;

        for(Map.Entry<Ticket, TicketType> entry: ticketList.entrySet()) {
            TicketType ticketType = entry.getValue();
            Ticket ticket = entry.getKey();
            sum += ticket.getTicketPrice();

            switch (ticketType) {
                case MIN_MAX: {
                    checkInCount++;
                    checkInSum += ticket.getTicketPrice();
                    break;
                }
                case LOST_TICKET: {
                    lostTicketCount++;
                    lostTicketSum += ticket.getTicketPrice();
                    break;
                }
                case SPECIAL_EVENT: {
                    specialEventCount++;
                    specialEventSum += ticket.getTicketPrice();
                    break;
                }
                case PENDING: {
                   pendingCount++;
                   break;
                }

                default:
                    throw new IllegalStateException("Unexpected value: " + ticketType);
            }
        }

        System.out.printf(" Activity to Date\n" +
                "\n" +
                "\n" +
                "\n" +
                " $%.2f was collected from %d Check Ins\n" +
                "\n" +
                " $%.2f was collected from %d Special Events \n" +
                "\n" +
                " $%.2f was collected from %d Lost Tickets\n" +
                "\n" +
                "\n" +
                "\n" +
                " $%.2f was collected overall", (checkInSum / 100), checkInCount, (specialEventSum / 100), specialEventCount, (lostTicketSum / 100), lostTicketCount, (sum / 100));

    }

    /**
     * Searches a ticketList by an Id number for a Ticket
     * Assigns the TicketType Min Max and a check out time
     * @param ticketList whats being searched
     * @param id the search term
     * @return the ticket found
     */
    public static Ticket findTicketById(HashMap<Ticket, TicketType> ticketList, int id) {
        for(Ticket ticket: ticketList.keySet()) {

            if(ticket.getTicketNumber() == id) {
                ticket.setTicketType(TicketType.MIN_MAX);
                ticket.calcCheckOutTime();
            } else {
                ticket = new Ticket();
                ticket.setTicketNumber(ticketList.size());
                ticket.calcCheckInTime();
                ticket.calcCheckOutTime();
                ticket.setTicketType(TicketType.MIN_MAX);
            }
            return ticket;
        }
        return null;

    }

    /**
     * Searches the ticketList for a ticket that is pending
     * removes the pending ticket and replaces it with a lost ticket
     * @param ticketList what's being searched
     * @return the ticket that was pending
     */
    public static Ticket findAPendingTicket(HashMap<Ticket, TicketType> ticketList) {
        for(Map.Entry<Ticket, TicketType> entry: ticketList.entrySet()) {
            Ticket ticket = entry.getKey();
            TicketType ticketType = entry.getValue();

            if(ticketType.equals(TicketType.PENDING)) {
                ticketList.remove(ticket, TicketType.PENDING);
                ticket.setTicketType(TicketType.LOST_TICKET);
                ticketList.put(ticket, ticket.getTicketType());
            } else {
                ticket = new Ticket();
                ticket.setTicketNumber(ticketList.size());

                ticket.setTicketType(TicketType.LOST_TICKET);
            }
            return ticket;
        }
        return null;
    }
}
