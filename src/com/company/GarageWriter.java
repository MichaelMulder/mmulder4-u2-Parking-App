package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class GarageWriter {
    /**
     *
     * @param fileName
     * @param ticketList
     */
    public static void writeTicketFile(String fileName, HashMap<Vehicle, TicketType> ticketList) {
        //write to file
        try {
            FileOutputStream writeData = new FileOutputStream(fileName);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(ticketList);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
