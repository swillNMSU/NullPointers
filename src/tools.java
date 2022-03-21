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
   // static pet[] pets = new pet[100];

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
            String line = "";
            String[] tempArr;

            //splite lines and store data into newObjects.
            while((line=br.readLine()) != null){
                tempArr = line.split(":");
               
                
                String[] ownerData = tempArr[0].split(",");
                owner newOwner = new owner(ownerData[0]);
                newOwner.setIncomeProof(ownerData[1]);
                newOwner.setStrikes(Integer.parseInt(ownerData[2]));
                newOwner.setNumRecieved(Integer.parseInt(ownerData[3]));
                
                // split pet data.
                // String[] petSplit = tempArr[1].split(";");
                
                
                // for (int j = 0; j < petSplit.length; j++){
                //     String[] petData = petSplit[j].split(",");
                    
                //     Boolean tBool = false;
                //     if (petData[2] == "t") tBool = true;
                //     if (petData[2] == "f") tBool = false;
                //     pet newPet = new pet(petData[0], petData[1], tBool);
                    
                //     newOwner.addPet(newPet);
                    

                // } // end for loop

                //Print owners and pets.  
                System.out.println(newOwner);
                // for (int i = 0; i < newOwner.numPets; i++){
                //     System.out.println(newOwner.ownersPets[i]);
                // }        
                owners[numOwners++] = newOwner;    // temporarily stored in an array. Needs to be arraylist.               
                System.out.println();
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
       
        
    }

    public static void main(String[] args) {

       System.out.println("\n\n\nRead from CSV file:\n"); 
       readCSV();

 
        

    }


}