package com.company;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class GarageReader {
        private static GarageReader instance = new GarageReader();

        private GarageReader() {
           try {
               readTicketsFile("TicketsDB.ser");
           }
           catch (Exception e) {
               e.printStackTrace();
           }
        }
        public HashMap<Ticket, TicketType> readTicketsFile(String fileName) {
        HashMap<Ticket, TicketType> ticketList = new HashMap<>();
            try {
                FileInputStream readData = new FileInputStream(fileName);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                ticketList = (HashMap<Ticket, TicketType>) readStream.readObject();
                readStream.close();
                System.out.println("loaded Tickets file");
                return  ticketList;
            } catch (Exception e) {
                e.printStackTrace();
            try {
                GarageWriter.writeTicketFile(fileName, ticketList);
                System.out.println("Created new TicketsDB");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return ticketList;
    }

    public static GarageReader getInstance() {
            return instance;
    }
}
