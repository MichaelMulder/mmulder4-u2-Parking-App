package com.company;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class GarageReader {
        public static HashMap<Vehicle, TicketType> readTicketsFile(String fileName) {
        HashMap<TicketType, Vehicle> ticketList = new HashMap<>();
        try {
            FileInputStream readData = new FileInputStream(fileName);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ticketList = (HashMap<TicketType, Vehicle>) readStream.readObject();
            readStream.close();
            return  ticketList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ticketList;
    }

}
