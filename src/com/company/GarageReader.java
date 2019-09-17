package com.company;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class GarageReader {
    public static List<Parkable> readGarageFile(String fileName) {
        List<Parkable> parkingList = null;
        try {
            FileInputStream readData = new FileInputStream(fileName);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            parkingList = (List<Parkable>) readStream.readObject();
            readStream.close();
            return  parkingList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parkingList;
    }

}
