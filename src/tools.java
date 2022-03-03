package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

/*
Tools to implement our CSV file.

*/

public class tools {


    public static void readCSV(){
        
        owner[] owners = new owner[100];
        pet[] pets = new pet[100];
        
        try{
            File theFile = new File("csvTest.csv");
            FileReader fr = new FileReader(theFile);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line=br.readLine()) != null){
                tempArr = line.split(":");
                int i = 0;
                for(String tempStr : tempArr) {
                    String[] ownerData = tempArr[0].split(",");
                    owner newOwner = new owner(ownerData[0]);
                    newOwner.setIncomeProof(ownerData[1]);
                    newOwner.setStrikes(Integer.parseInt(ownerData[2]));
                    newOwner.setNumRecieved(Integer.parseInt(ownerData[3]));
                    
                    // split pet data.
                    String[] petSplit = tempArr[1].split(",");
                    newOwner.setNumPets(petSplit.length);



                    System.out.println(tempStr + " ");
                    i++;
                }
                System.out.println();
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        
        //Temp console display to test tools
        System.out.println("Enter your choice:\n\t1. Display All\n\t2. Add Owner\n\t3. Remove Owner\n\t4. View Owner");
        System.out.println("\n\n");
        readCSV();

    }


}
