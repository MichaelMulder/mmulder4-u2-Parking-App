package com.company;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class GarageReader {
        public static HashMap<Vehicle, TicketType> readTicketsFile(String fileName) {
        HashMap<Vehicle, TicketType> ticketList = new HashMap<>();
        try {
            FileInputStream readData = new FileInputStream(fileName);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ticketList = (HashMap<Vehicle, TicketType>) readStream.readObject();
            readStream.close();
            System.out.println("loaded Tickets file");
            return  ticketList;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                GarageWriter.writeTicketFile("TicketsDB.ser", ticketList);
                System.out.println("Created new TicketsDB");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return ticketList;
    }

}
