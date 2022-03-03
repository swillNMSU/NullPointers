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

    static owner[] owners = new owner[10];  // these need to be dynamic in the future. Probably should use linked list or collections.
    static int numOwners = 0;
    static pet[] pets = new pet[100];

    public static void readCSV(){
        
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
                    String[] petSplit = tempArr[1].split(";");
                    
                    
                    for (int j = 0; j < petSplit.length; j++){
                        String[] petData = petSplit[j].split(",");
                        
                        Boolean tBool = false;
                        if (petData[2] == "t") tBool = true;
                        if (petData[2] == "f") tBool = false;
                        pet newPet = new pet(petData[0], petData[1], tBool);
                        
                        newOwner.addPet(newPet);

                    } // end for loop



                    
                    owners[numOwners++] = newOwner;
                    i++;
                }
                System.out.println("\n\n");
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        
        //Temp console display to test tools
        //System.out.println("Enter your choice:\n\t1. Display All\n\t2. Add Owner\n\t3. Remove Owner\n\t4. View Owner");
       // System.out.println("\n\n");
        readCSV();

 
        for (owner ows : owners) {
            System.out.println(ows);
            for (int j = 0; j < ows.getNumPets(); j++){
                System.out.println(ows.ownersPets[j]);
            }
        }

    }


}