package com.company;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class GarageReader {
    public static List<Parkable> readGarageFile(String fileName) {
        List<Parkable> roomList = null;
        try {
            FileInputStream readData = new FileInputStream(fileName);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            roomList = (List<Parkable>) readStream.readObject();
            readStream.close();
            return  roomList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomList;
    }

}
