package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

/*
Tools to implement our CSV file.

*/

public class Read {

    static int numOwners = 0;

    /**
     *  Call to read a CSV file.
     *     CSV reading is strucured as follows:
     * 
     *      ':' seperates all owner and pet objects into their own substring,
     *       with owners being stored in arr[0], pets in arr[1], arr[2]...
     * 
     *      ',' seperates these substrings into the objects data members,
     *      which are then converted to the appropriate type and stored in the objects
     */
    public static void readCSV(){
       
        try{
            File theFile = new File("csvTest.csv");
            FileReader fr = new FileReader(theFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            
            //splite lines and store data into newObjects.
            while(line != null){    
                
                String[] ownerData = line.split(",");
                Owner nOwner = new Owner(ownerData[0]);
                nOwner.setIncomeProof(Boolean.parseBoolean(ownerData[1]));
                nOwner.setStrikes(Integer.parseInt(ownerData[2])); 
                nOwner.setNumRecieved(Integer.parseInt(ownerData[3]));
                nOwner.setNumPets(Integer.parseInt(ownerData[4]));
                //System.out.println(nOwner+"\n");
                Driver.owners.add(nOwner); 
                 
                 
               // System.out.println(); 
                line = br.readLine();      
            }
            br.close();
            //TODO: sort the list.
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

}